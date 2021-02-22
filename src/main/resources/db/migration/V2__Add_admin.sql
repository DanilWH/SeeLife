SELECT next_val into @hib_next_val FROM hibernate_sequence;

INSERT INTO user (id, active, username, password)
    VALUES (@hib_next_val, true, 'sl_admin', '341021dan');

INSERT INTO user_role (user_id, roles)
    VALUES (@hib_next_val, 'USER'), (@hib_next_val, 'ADMIN');

UPDATE hibernate_sequence SET next_val = @hib_next_val + 1;