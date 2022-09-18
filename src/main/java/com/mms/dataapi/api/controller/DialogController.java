package com.mms.dataapi.api.controller;

import com.mms.dataapi.api.model.DataPayload;
import com.mms.dataapi.api.model.Dialog;
import com.mms.dataapi.api.model.Utterance;
import com.mms.dataapi.api.repository.DialogRepository;
import com.mms.dataapi.api.repository.UtteranceRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mms.dataapi.api.repository.UtteranceSpecification.hasConsent;
import static com.mms.dataapi.api.repository.UtteranceSpecification.hasLanguage;

@RestController
@RequestMapping("/")
public class DialogController {
    private final DialogRepository dialogRepository;
    private final UtteranceRepository utteranceRepository;

    public DialogController(DialogRepository dialogRepository, UtteranceRepository utteranceRepository) {
        this.dialogRepository = dialogRepository;
        this.utteranceRepository = utteranceRepository;
    }

    @PostMapping("/data/{customerId}/{dialogId}")
    ResponseEntity<Utterance> postData(
            @PathVariable(value="customerId") Long customerId,
            @PathVariable(value="dialogId") Long dialogId,
            @RequestBody DataPayload newEntry) {
        //curl -X POST localhost:8080/data/2/23 -H 'Content-type:application/json' -d '{"text":"Hey hey", "language":"EN"}'

        Optional<Dialog> dialogue = dialogRepository.findById(dialogId);
        if (dialogue.isPresent()) {
            if (!dialogue.get().getCustomerId().equals(customerId)) {
                // This dialogue exists with another customerId
                return new ResponseEntity("409 CONFLICT - A dialogue already exists for another customer.",
                        HttpStatus.CONFLICT);
            }
            // else: This dialogue already exists, no need to create it
        } else {
            // This dialogue does not exist, let's create one
            Dialog d = new Dialog(dialogId, customerId);
            dialogue = Optional.of(d);
            dialogRepository.save(d);
        }
        Utterance u = new Utterance(dialogue.get(), newEntry.getText(), newEntry.getLanguage());
        utteranceRepository.save(u);

        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @PostMapping("/consents/{dialogId}")
    ResponseEntity postConsents(
            @PathVariable(value="dialogId") Long dialogId,
            @RequestBody @NotNull Boolean consent) {

        Optional<Dialog> dialogue = dialogRepository.findById(dialogId);
        if (dialogue.isPresent()) {
            if (!dialogue.get().getConsent()) {
                // The current consent is false
                if (consent) {
                    // New consent is true --> we save it
                    dialogue.get().setConsent(consent);
                    dialogRepository.save(dialogue.get());
                    return ResponseEntity.status(HttpStatus.CREATED).body("Consent was given.");
                } else {
                    // New consent is false --> we remove the data from the DB
                    dialogRepository.delete(dialogue.get());
                    return ResponseEntity.status(HttpStatus.CREATED).body("Consent was rejected, data was cleaned.");
                }
            }
            // else: consent was previously set to true
            return new ResponseEntity(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No dialogue was found for this dialogId.");
    }

    @GetMapping("/data")
    public List<Utterance> getData(
            @RequestParam("language") Optional<String> language,
            @RequestParam("customerId") Optional<Long> customerId,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize
            ){

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("creationDate").descending());
        Page<Utterance> pagedResult = utteranceRepository.findAll(hasConsent(customerId).and(hasLanguage(language)), paging);

        if(pagedResult != null && pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Utterance>();
        }
    }
}
