SELECT first_name, birthday
FROM users
WHERE birthday > '1999-10-23 00:00:00'
ORDER BY first_name
LIMIT 3