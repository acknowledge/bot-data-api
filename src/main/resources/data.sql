insert into dialog(dialog_id, consent, customer_id) values(5, FALSE, 1);
insert into dialog(dialog_id, consent, customer_id) values(6, FALSE, 1);
insert into dialog(dialog_id, consent, customer_id) values(7, TRUE, 1);
insert into dialog(dialog_id, consent, customer_id) values(8, TRUE, 2);
insert into utterance(utterance_id, dialog_id, language, text, creation_date) values(random_uuid(), 5, 'EN', 'Hi', '2022-09-17T22:52:57.032234');
insert into utterance(utterance_id, dialog_id, language, text, creation_date) values(random_uuid(), 6, 'FR', 'Salut', '2022-09-17T22:56:57.032234');
insert into utterance(utterance_id, dialog_id, language, text, creation_date) values(random_uuid(), 7, 'EN', 'I want to modify my contract', '2022-09-17T23:15:57.032234');
insert into utterance(utterance_id, dialog_id, language, text, creation_date) values(random_uuid(), 7, 'EN', 'Yes indeed', '2022-09-17T23:16:57.032234');
insert into utterance(utterance_id, dialog_id, language, text, creation_date) values(random_uuid(), 7, 'EN', 'Ok, thanks a lot', '2022-09-17T23:17:57.032234');
insert into utterance(utterance_id, dialog_id, language, text, creation_date) values(random_uuid(), 8, 'DE', 'Hallo', '2022-09-18T14:18:00.032234');
insert into utterance(utterance_id, dialog_id, language, text, creation_date) values(random_uuid(), 8, 'DE', 'Ja, gerne', '2022-09-18T14:19:00.032234');
