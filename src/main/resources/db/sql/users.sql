CREATE TABLE user_table
(
    user_id    UUID DEFAULT uuid_generate_v4() PRIMARY KEY NOT NULL,
    user_login TEXT                                        NOT NULL,
    pass_hash  TEXT                                        NOT NULL
);

INSERT INTO user_table(user_id, user_login, pass_hash)
VALUES ('00000000-0000-0000-0000-000000000000', 'eltgm',
        '$2a$10$2CjV9j/YLU3Uy8jbZi2S..LdK6vLnVbMylLNC.4GWxHjrZ94T.72y'),
       ('00000000-0000-0000-0000-000000000001', 'user',
        '$2a$10$2CjV9j/YLU3Uy8jbZi2S..LdK6vLnVbMylLNC.4GWxHjrZ94T.72y'); -- pass hash for 12344321