INSERT INTO users (id, name, email, password) VALUES
  (1, 'admin', 'admin@example.com', '$2y$12$szVD/uFwP.gYkli.BABbc.UCnjOaPT762vB1lO7ew69FC78cfQMvu'),
  (2, 'user', 'user@example.com', '$2y$12$OHhqPF14tyHoJD1qHXPeqeIULfJBO5ug/IrzJEq0JVLa9iT8MWSfC')
  ON CONFLICT DO NOTHING;
  
INSERT INTO roles (id, name) VALUES
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_USER')
  ON CONFLICT DO NOTHING;

INSERT INTO users_roles (user_id, role_id) VALUES
  (1, 1),
  (1, 2),
  (2, 2)
  ON CONFLICT DO NOTHING;