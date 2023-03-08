
INSERT INTO `currency_type` (currency_type_id ,currency) VALUES (1,'AUD'),(2,'SGD'),(3,'USD');
INSERT INTO `user` (user_id,created_at,updated_at,user_name) VALUES (1,'2022-04-22 10:34:23','2022-04-22 10:34:23','oneuser'),(2,'2022-04-23 10:34:23','2022-04-24 10:34:23','twouser');
INSERT INTO `account_type` (account_type_id ,account_type) VALUES (1,'Savings'),(2,'Current');
INSERT INTO `account` (created_at,updated_at,account_name,available_balance,balance_date,account_type_id,currency_type_id,user_id) VALUES
	 ('2023-01-22 10:34:23','2023-01-22 10:34:23','AUSavings23',12.34,'2023-01-22 10:34:23',1,1,1),
	 ('2023-01-22 10:34:23','2023-01-22 10:34:23','SGSavings2334',34.67,'2023-01-22 10:34:23',2,2,2),
	 ('2022-01-22 10:34:23','2022-01-22 10:34:23','USSavings33',1234.0,'2022-01-22 10:34:23',1,3,1);
INSERT INTO `transaction` (created_at,updated_at,credit_amount,debit_amount,transaction_narrative,transaction_type,value_date,account_id) VALUES
	 ('2022-01-22 10:34:23','2022-01-22 10:34:23',6753.3,NULL,NULL,'Credit','2022-01-22 10:34:23',1),
	 ('2022-01-22 10:34:23','2022-01-22 10:34:23',NULL,564.6,NULL,'Debit','2022-01-22 10:34:23',1),
	 ('2022-01-22 10:34:23','2022-01-22 10:34:23',4545.09,NULL,NULL,'Credit','2022-01-22 10:34:23',2);

