# ClinicaHealthStat
Projeto para conclusão de curso 

•	Postgres Versão 9.1
•	IDE: Eclipse 2018-12  Versão  (4.10.0)
•	Hibernate3
•	Jpa Versão 2.0
•	Spring Versão 3.0.5
•	Spring Security Versão 3.1.22
•	PrimeFaces  Versão 6.2
•	JSF Versão 2.2.7
•	TomCat Versão 7.0
•	Jasper Report Versão 5.6.0


*Implementando Agendamento


Script Inicial do para o banco de dados

/*Rodar para abrir acesso no sistema*/

		INSERT INTO 	public.estado( estado_id, estado_nome, estado_uf) VALUES (1,'Acre', 'AC' ,1, 1);
		INSERT INTO		public.estado( estado_id, estado_nome, estado_uf) VALUES (2,'Alagoas', 'AL' , 1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (3, 'Amapá', 'AP', 1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (4, 'Amazonas', 'AM',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (5, 'Bahia', 'BA',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (6, 'Ceará', 'CE',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (7, 'Distrito Federal', 'DF',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (8, 'Espírito Santo', 'ES',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (9, 'Goiás', 'GO',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (10, 'Maranhão', 'MA',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (11, 'Mato Grosso', 'MT',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (12, 'Mato Grosso do Sul', 'MS',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (13, 'Minas Gerais', 'MG',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (14, 'Pará', 'PA',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (15, 'Paraíba', 'PB',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (16, 'Paraná', 'PR', 1,1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (17, 'Pernambuco', 'PE', 1,1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (18, 'Piauí', 'PI',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (19, 'Rio de Janeiro', 'RJ',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (20, 'Rio Grande do Norte', 'RN', 1,1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (21, 'Rio Grande do Sul', 'RS',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (22, 'Rondônia', 'RO',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (23, 'Roraima', 'RR',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (24, 'Santa Catarina', 'SC',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (25, 'São Paulo', 'SP',1, 1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (26, 'Sergipe', 'SE', 1,1);
		INSERT INTO     public.estado( estado_id, estado_nome, estado_uf) VALUES (27, 'Tocantins', 'TO',1, 1);
		
	
		INSERT INTO cidade VALUES	(	1	,	 'Ampere'	,	 16);
		INSERT INTO cidade VALUES	(	2	,	 'Anahy'	,	 16);
		INSERT INTO cidade VALUES	(	3	,	 'Andira'	,	 16);
		INSERT INTO cidade VALUES	(	4	,	 'Andorinhas',	 16);
		INSERT INTO cidade VALUES	(	5	,	 'Angai'	,	 16);
		INSERT INTO cidade VALUES	(	6	,	 'Angulo'	,	 16);
		INSERT INTO cidade VALUES	(	7	,	 'Antas'	,	 16);
		INSERT INTO cidade VALUES	(	8	,	 'Antonina'	,	 16);
		INSERT INTO cidade VALUES	(	9	,	 'Antonio Brandao de Oliveira'	,	 16);
		INSERT INTO cidade VALUES	(	10	,	 'Antonio Olinto'	,	 16);
		INSERT INTO cidade VALUES	(	11	,	 'Anunciacao'	,	 16);
		INSERT INTO cidade VALUES	(	12	,	 'Aparecida do Oeste'	,	 16);
		INSERT INTO cidade VALUES	(	13	,	 'Apiaba'	,	 16);
		INSERT INTO cidade VALUES	(	14	,	 'Apucarana'	,	 16);
		INSERT INTO cidade VALUES	(	15	,	 'Apucaraninha'	,	 16);
		INSERT INTO cidade VALUES	(	16	,	 'Aquidaban'	,	 16);		

		
		
		
		/*Função retira acentos da pesquisa*/
			CREATE OR REPLACE FUNCTION public.retira_acentos(text)
			  RETURNS text AS
			$BODY$ 
			select 
			translate($1,'áàâãäéèêëíìïóòôõöúùûüÁÀÂÃÄÉÈÊËÍÌÏÓÒÔÕÖÚÙÛÜçÇ', 
			'aaaaaeeeeiiiooooouuuuAAAAAEEEEIIIOOOOOUUUUcC'); 
			$BODY$
			  LANGUAGE sql IMMUTABLE STRICT
			  COST 100;
			ALTER FUNCTION public.retira_acentos(text)
			  OWNER TO postgres;
		
    
		/*Acesso*/
		INSERT INTO public.entidade(ent_codigo, ent_inativo, ent_login, ent_senha)  VALUES (1, FALSE, 'admin', 123);
		INSERT INTO public.entidadeacesso(ent_codigo, esa_codigo) VALUES (1,'ADMIN');
		INSERT INTO public.entidadeacesso(ent_codigo, esa_codigo)VALUES (1,'USER');

			
			  
			  

