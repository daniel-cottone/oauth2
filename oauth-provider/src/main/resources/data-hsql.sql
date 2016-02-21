insert into users(id,username,password,last_change,authorities) values(users_seq.nextval,'user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC',null,'ROLE_USER')
insert into users(id,username,password,last_change,authorities) values(users_seq.nextval,'admin','$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi',null,'ROLE_ADMIN')

insert into clients(id,client_id,client_secret,resource_ids,scopes,grant_types,redirect_uris,auto_approve_scopes) values(clients_seq.nextval,'acme','acmesecret',null,'openid','authorization_code,implicit,password,refresh_token',null,'openid')
