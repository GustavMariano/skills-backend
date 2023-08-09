CREATE DATABASE desafio_tecnico;

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    login VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE skills (
    id SERIAL PRIMARY KEY,
    url VARCHAR(500),
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(150)
);

CREATE TABLE usuarios_skills (
    id SERIAL NOT NULL,
    usuario_id INT NOT NULL,
    skill_id INT NOT NULL,
    level INT NOT NULL,
    CONSTRAINT fk_usuario_skills_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_usuario_skills_skill_id FOREIGN KEY(skill_id) REFERENCES skills(id)
);

INSERT INTO skills (url, nome, descricao) VALUES
    ('https://cdn.icon-icons.com/icons2/2415/PNG/512/java_original_wordmark_logo_icon_146459.png', 'Java', 'Desenvolvedor Java'),
    ('https://logos-world.net/wp-content/uploads/2021/10/Python-Symbol.png', 'Python', 'Desenvolvedor Python'),
    ('https://upload.wikimedia.org/wikipedia/commons/thumb/6/6a/JavaScript-logo.png/800px-JavaScript-logo.png', 'JavaScript', 'Desenvolvedor JavaScript.'),
    ('https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/ISO_C%2B%2B_Logo.svg/1822px-ISO_C%2B%2B_Logo.svg.png', 'C++', 'Desenvolvedor C++.'),
    ('https://seeklogo.com/images/C/c-sharp-c-logo-02F17714BA-seeklogo.com.png', 'C#', 'Desenvolvedor C#.'),
    ('https://cdn.icon-icons.com/icons2/2415/PNG/512/ruby_original_wordmark_logo_icon_146364.png', 'Ruby', 'Desenvolvedor Ruby.'),
    ('https://cdn.icon-icons.com/icons2/2699/PNG/512/swift_vertical_logo_icon_168769.png', 'Swift', 'Desenvolvedor Swift.'),
    ('https://download.logo.wine/logo/Go_(programming_language)/Go_(programming_language)-Logo.wine.png', 'Go', 'Desenvolvedor Go.'),
    ('https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/PHP-logo.svg/2560px-PHP-logo.svg.png', 'PHP', 'Desenvolvedor PHP.'),
    ('https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/R_logo.svg/2560px-R_logo.svg.png', 'R', 'Desenvolvedor R.');
