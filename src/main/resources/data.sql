INSERT INTO ratings (id, rating, description) VALUES
    (1, 'G', 'General Audience - Suitable for all ages'),
    (2, 'PG', 'Parental Guidance Suggested'),
    (3, 'PG-13', 'Parents Strongly Cautioned - Some material may be inappropriate for children under 13'),
    (4, 'R', 'Restricted - Under 17 requires accompanying parent or adult guardian'),
    (5, 'NC-17', 'Adults Only - No one 17 and under admitted');

INSERT INTO genres (id, genre, description) VALUES
    (1, 'Action', 'Fast-paced and full of excitement, featuring physical stunts and chases'),
    (2, 'Comedy', 'Intended to make audiences laugh through amusement'),
    (3, 'Drama', 'Character-driven stories focused on realistic plots and emotional themes'),
    (4, 'Horror', 'Intended to frighten and scare the audience'),
    (5, 'Science Fiction', 'Speculative fiction with imaginative but scientific concepts'),
    (6, 'Adventure', 'Exciting stories, usually involving danger and action'),
    (7, 'Romance', 'Focus on romantic love stories between characters'),
    (8, 'Thriller', 'Designed to keep viewers on the edge of their seats'),
    (9, 'Fantasy', 'Features magical and supernatural elements'),
    (10, 'Animation', 'Films created using animation techniques');

INSERT INTO directors (first_name, last_name, date_of_birth) VALUES
    ('Steven', 'Spielberg', '1946-12-18'),
    ('Martin', 'Scorsese', '1942-11-17'),
    ('Christopher', 'Nolan', '1970-07-30'),
    ('Quentin', 'Tarantino', '1963-03-27'),
    ('Peter', 'Jackson', '1961-10-31');

INSERT INTO actors (first_name, last_name, date_of_birth) VALUES
    ('Tom', 'Hanks', '1956-07-09'),
    ('Leonardo', 'DiCaprio', '1974-11-11'),
    ('Morgan', 'Freeman', '1937-06-01'),
    ('Brad', 'Pitt', '1963-12-18'),
    ('Meryl', 'Streep', '1949-06-22'),
    ('Jennifer', 'Lawrence', '1990-08-15'),
    ('Robert', 'Downey Jr.', '1965-04-04'),
    ('Scarlett', 'Johansson', '1984-11-22'),
    ('Denzel', 'Washington', '1954-12-28'),
    ('Emma', 'Stone', '1988-11-06');

INSERT INTO movies (movie_title, director_id, release_date, genre_id, rating_id, movie_length, trailer_url, external_id, overview) VALUES
    ('Jurassic Park', 1, '1993-06-11', 1, 3, 127, 'https://www.youtube.com/watch?v=QWBKEmWWL38', 329, 
    'During a preview tour, a theme park suffers a major power breakdown that allows its cloned dinosaur exhibits to run amok.'),
    
    ('The Departed', 2, '2006-10-06', 8, 4, 151, 'https://www.youtube.com/watch?v=iojhqm0JTW4', 1422, 
    'An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston.'),
    
    ('Inception', 3, '2010-07-16', 5, 3, 148, 'https://www.youtube.com/watch?v=YoHD9XEInc0', 27205,
    'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.'),
    
    ('Pulp Fiction', 4, '1994-10-14', 8, 4, 154, 'https://www.youtube.com/watch?v=s7EdQ4FqbhY', 680,
    'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.'),
    
    ('The Lord of the Rings', 5, '2001-12-19', 9, 3, 178, 'https://www.youtube.com/watch?v=V75dMMIW2B4', 120,
    'A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.');

INSERT INTO movie_actors (movie_id, actor_id) VALUES
    (1, 1),  -- Tom Hanks in Jurassic Park
    (2, 2),  -- Leonardo DiCaprio in The Departed
    (3, 2),  -- Leonardo DiCaprio in Inception
    (4, 4),  -- Brad Pitt in Pulp Fiction
    (5, 1);  -- Tom Hanks in Lord of the Rings 