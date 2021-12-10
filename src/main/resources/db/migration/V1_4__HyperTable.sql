ALTER TABLE product ADD PRIMARY KEY (id, time);
SELECT create_hypertable('product', 'time');