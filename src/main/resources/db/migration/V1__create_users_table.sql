CREATE TABLE IF NOT EXISTS users (
	user_id SERIAL PRIMARY KEY,
	public_id UUID DEFAULT gen_random_uuid() NOT NULL,
	email VARCHAR(255) UNIQUE NOT NULL,
	hash_password VARCHAR(255) NOT NULL,
	created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
	CONSTRAINT uq_users_public_id UNIQUE (public_id)
);