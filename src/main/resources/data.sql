insert into dialog(dialog_id, consent, customer_id) values(5, FALSE, 12);
insert into dialog(dialog_id, consent, customer_id) values(6, FALSE, 12);
insert into dialog(dialog_id, consent, customer_id) values(7, TRUE, 44);
insert into utterance(utterance_id, dialog_id, language, text, creation_date) values(1, 5, 'en', 'Hi', '2022-09-17T22:52:57.032234');
insert into utterance(utterance_id, dialog_id, language, text, creation_date) values(2, 6, 'fr', 'Salut', '2022-09-17T22:56:57.032234');
insert into utterance(utterance_id, dialog_id, language, text, creation_date) values(3, 7, 'en', 'I want to modify my contract', '2022-09-17T23:15:57.032234');
insert into utterance(utterance_id, dialog_id, language, text, creation_date) values(4, 7, 'en', 'Yes indeed', '2022-09-17T23:16:57.032234');
insert into utterance(utterance_id, dialog_id, language, text, creation_date) values(5, 7, 'en', 'Ok, thanks a lot', '2022-09-17T23:17:57.032234');
