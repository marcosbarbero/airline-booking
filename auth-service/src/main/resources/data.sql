insert into oauth_client_details (client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity)
VALUES
  ('booking-app', '$2a$10$PUAY4YbwnimRly2GsWVK2e9ZxQNqJIBhaz0wrY8B/cUU9sHfdP8Uq', 'read,write', 'password,client_credentials,refresh_token', 'ROLE_CLIENT', 300);