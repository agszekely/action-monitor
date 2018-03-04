INSERT INTO bvmessages(id, message) VALUES('tesztid', 'uzenet');
UPDATE bvmessages set MESSAGE = 'updated', MODIFIED = CURRENT_TIMESTAMP WHERE id = 'tesztid';