CREATE TABLE PLAYER
(
    id         INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name       VARCHAR NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);

INSERT INTO PLAYER (id, name)
VALUES (1, 'tihlok'),
       (2, 'jaq'),
       (3, 'nhamnham');
