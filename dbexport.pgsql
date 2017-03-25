--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.1
-- Dumped by pg_dump version 9.6.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: postgis; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA public;


--
-- Name: EXTENSION postgis; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION postgis IS 'PostGIS geometry, geography, and raster spatial types and functions';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE account (
    id integer NOT NULL,
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255),
    role integer,
    city_id integer NOT NULL
);


ALTER TABLE account OWNER TO postgres;

--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE category (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE category OWNER TO postgres;

--
-- Name: city; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE city (
    id integer NOT NULL,
    name character varying(255),
    country_id integer NOT NULL,
    boundary geometry
);


ALTER TABLE city OWNER TO postgres;

--
-- Name: country; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE country (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE country OWNER TO postgres;

--
-- Name: diningtable; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE diningtable (
    id integer NOT NULL,
    amount integer,
    restaurant_id integer NOT NULL,
    persons integer
);


ALTER TABLE diningtable OWNER TO postgres;

--
-- Name: diningtabletype; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE diningtabletype (
    id integer NOT NULL,
    name character varying(255),
    persons integer
);


ALTER TABLE diningtabletype OWNER TO postgres;

--
-- Name: forgotpassword; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE forgotpassword (
    id integer NOT NULL,
    token character varying(255),
    user_id integer NOT NULL
);


ALTER TABLE forgotpassword OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- Name: location; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE location (
    id integer NOT NULL,
    name character varying(255),
    city_id integer NOT NULL,
    lat_long geometry
);


ALTER TABLE location OWNER TO postgres;

--
-- Name: menu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE menu (
    id integer NOT NULL,
    name character varying(255),
    restaurant_id integer NOT NULL
);


ALTER TABLE menu OWNER TO postgres;

--
-- Name: menuitem; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE menuitem (
    id integer NOT NULL,
    description character varying(255),
    name character varying(255),
    price numeric(19,2),
    sort double precision,
    menu_id integer NOT NULL
);


ALTER TABLE menuitem OWNER TO postgres;

--
-- Name: photo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE photo (
    id integer NOT NULL,
    image_url character varying(255),
    sort double precision,
    restaurant_id integer NOT NULL
);


ALTER TABLE photo OWNER TO postgres;

--
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE reservation (
    id integer NOT NULL,
    created_at timestamp without time zone,
    for_time timestamp without time zone,
    persons integer,
    request character varying(255),
    account_id integer,
    table_id integer
);


ALTER TABLE reservation OWNER TO postgres;

--
-- Name: restaurant; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE restaurant (
    id integer NOT NULL,
    active_menu integer,
    cover_image_url character varying(255),
    description character varying(255),
    logo_image_url character varying(255),
    name character varying(255),
    pricing integer,
    review_count integer,
    review_rating double precision,
    location_id integer,
    lat_long geometry,
    city_id integer NOT NULL
);


ALTER TABLE restaurant OWNER TO postgres;

--
-- Name: restaurantcategory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE restaurantcategory (
    restaurant_id integer NOT NULL,
    category_id integer NOT NULL
);


ALTER TABLE restaurantcategory OWNER TO postgres;

--
-- Name: review; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE review (
    id integer NOT NULL,
    created_at timestamp without time zone,
    description character varying(255),
    rating integer,
    account_id integer NOT NULL,
    restaurant_id integer NOT NULL
);


ALTER TABLE review OWNER TO postgres;

--
-- Name: 18222; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18222');


ALTER LARGE OBJECT 18222 OWNER TO postgres;

--
-- Name: 18226; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18226');


ALTER LARGE OBJECT 18226 OWNER TO postgres;

--
-- Name: 18227; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18227');


ALTER LARGE OBJECT 18227 OWNER TO postgres;

--
-- Name: 18228; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18228');


ALTER LARGE OBJECT 18228 OWNER TO postgres;

--
-- Name: 18229; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18229');


ALTER LARGE OBJECT 18229 OWNER TO postgres;

--
-- Name: 18230; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18230');


ALTER LARGE OBJECT 18230 OWNER TO postgres;

--
-- Name: 18231; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18231');


ALTER LARGE OBJECT 18231 OWNER TO postgres;

--
-- Name: 18232; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18232');


ALTER LARGE OBJECT 18232 OWNER TO postgres;

--
-- Name: 18233; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18233');


ALTER LARGE OBJECT 18233 OWNER TO postgres;

--
-- Name: 18234; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18234');


ALTER LARGE OBJECT 18234 OWNER TO postgres;

--
-- Name: 18236; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18236');


ALTER LARGE OBJECT 18236 OWNER TO postgres;

--
-- Name: 18237; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18237');


ALTER LARGE OBJECT 18237 OWNER TO postgres;

--
-- Name: 18238; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18238');


ALTER LARGE OBJECT 18238 OWNER TO postgres;

--
-- Name: 18239; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18239');


ALTER LARGE OBJECT 18239 OWNER TO postgres;

--
-- Name: 18240; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18240');


ALTER LARGE OBJECT 18240 OWNER TO postgres;

--
-- Name: 18241; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18241');


ALTER LARGE OBJECT 18241 OWNER TO postgres;

--
-- Name: 18242; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18242');


ALTER LARGE OBJECT 18242 OWNER TO postgres;

--
-- Name: 18243; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18243');


ALTER LARGE OBJECT 18243 OWNER TO postgres;

--
-- Name: 18244; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18244');


ALTER LARGE OBJECT 18244 OWNER TO postgres;

--
-- Name: 18245; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18245');


ALTER LARGE OBJECT 18245 OWNER TO postgres;

--
-- Name: 18246; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18246');


ALTER LARGE OBJECT 18246 OWNER TO postgres;

--
-- Name: 18247; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18247');


ALTER LARGE OBJECT 18247 OWNER TO postgres;

--
-- Name: 18248; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18248');


ALTER LARGE OBJECT 18248 OWNER TO postgres;

--
-- Name: 18249; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18249');


ALTER LARGE OBJECT 18249 OWNER TO postgres;

--
-- Name: 18250; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18250');


ALTER LARGE OBJECT 18250 OWNER TO postgres;

--
-- Name: 18251; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18251');


ALTER LARGE OBJECT 18251 OWNER TO postgres;

--
-- Name: 18252; Type: BLOB; Schema: -; Owner: postgres
--

SELECT pg_catalog.lo_create('18252');


ALTER LARGE OBJECT 18252 OWNER TO postgres;

--
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY account (id, email, first_name, last_name, password, role, city_id) FROM stdin;
309	ahmed@gmail.com	Ahmed	Popo	$2a$10$pDCzv/xxx52FTi.OmhtuGeHqGttnirbQ.od2gFaDGDv0eey1Uxyqu	0	17
310	fefo@gmail.com	Testo	Testovic	$2a$10$pDCzv/xxx52FTi.OmhtuGeHqGttnirbQ.od2gFaDGDv0eey1Uxyqu	0	17
318	ahm@gmail.com	felo	melo	$2a$10$xHAH7EIwEnd0aIN3mxdlsuJDo.hl.MdNVfL292mwFqapwkqXkpTTu	0	17
28	testo@test.com	Testo	Testovic	$2a$10$e067qsZ/on0Gbasy4rzSaeYqyts9vtrR58UYTP.rQ/dIIT6YQgumy	0	17
319	t@t.com	t	t	$2a$10$VUeYb5UY1F2FiB03bdXNmutZYiCf/H.RUqc/flOJBhsWrW3WbFMfK	0	17
25	ahmed.popovic@gmail.com	Ahmed	Popovic	$2a$10$XWJiD2HYwfWvhtrClUyI/eEaDbsKMpS51u/tQ6IavnAMxf087xv5W	1	17
375	ahmo123@ahmo.com	Testiram	Lagano testiram	$2a$10$frkod49bx3LnMDC3RD8/x.wX.avCnzDTnc1boSsT4oJ1VjpEu9Yby	0	17
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY category (id, name) FROM stdin;
59	Italian
60	International
61	Mediterranean
\.


--
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY city (id, name, country_id, boundary) FROM stdin;
17	Sarajevo	0	\N
19	Goražde	0	\N
20	Zagreb	6	\N
22	Beograd	21	\N
23	Mostar	0	\N
399	Tuzla	0	\N
401	Barselona	7	\N
\.


--
-- Data for Name: country; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY country (id, name) FROM stdin;
0	Bosna i Hercegovina
8	Australija
9	Zimbabve
10	Filipini
7	Španija
4	Germany
16	Testtt
5	Netherlands
21	Srbija
6	Hrvatska
\.


--
-- Data for Name: diningtable; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY diningtable (id, amount, restaurant_id, persons) FROM stdin;
128	2	113	2
175	2	165	2
176	2	165	3
177	2	165	4
178	2	165	5
188	2	179	2
189	2	179	3
190	2	179	4
191	2	179	5
205	2	192	2
206	2	192	3
207	2	192	4
208	2	192	5
218	2	209	2
219	2	209	3
220	2	209	4
221	2	209	5
233	2	222	2
234	2	222	3
235	2	222	4
236	2	222	5
249	2	237	2
250	2	237	3
251	2	237	4
252	2	237	5
268	2	253	2
269	2	253	3
270	2	253	4
271	2	253	5
287	2	272	2
288	2	272	3
289	2	272	4
290	2	272	5
305	2	291	2
306	2	291	3
307	2	291	4
308	2	291	5
130	5	113	4
131	3	113	5
129	7	113	3
\.


--
-- Data for Name: diningtabletype; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY diningtabletype (id, name, persons) FROM stdin;
\.


--
-- Data for Name: forgotpassword; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY forgotpassword (id, token, user_id) FROM stdin;
311	8c5d53f8-fed1-43b7-8bae-b4c89b7c0663	25
312	058235ea-c4b1-4759-a2b6-22a1793ee8f9	25
313	50288319-f456-4478-aed2-7890a671a44c	25
314	4e8c9c39-a15d-4420-a35a-286d18378bc4	25
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 401, true);


--
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY location (id, name, city_id, lat_long) FROM stdin;
\.


--
-- Data for Name: menu; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY menu (id, name, restaurant_id) FROM stdin;
114	First menu	113
166	First menu	165
180	First menu	179
193	First menu	192
210	First menu	209
223	First menu	222
238	First menu	237
254	First menu	253
273	First menu	272
292	First menu	291
\.


--
-- Data for Name: menuitem; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY menuitem (id, description, name, price, sort, menu_id) FROM stdin;
167	Minus officia numquam voluptate quibusdam repellat officiis recusandae blanditiis odit.	odio voluptas	8.95	0	166
168	Et cum sit autem eos est amet ut non.	hic doloremque	14.93	1	166
169	Dolore nesciunt maxime quisquam.	ut quasi	8.57	2	166
170	Necessitatibus mollitia dicta vitae perspiciatis aut accusamus qui.	libero quaerat	7.26	3	166
171	Rem architecto facilis error quaerat quis quidem id.	sit voluptatem	22.54	4	166
172	Quo cumque blanditiis itaque nihil incidunt doloribus illo laudantium.	unde vitae	9.65	5	166
173	Praesentium voluptate aut quisquam corrupti.	iure aut	11.43	6	166
174	Incidunt tenetur expedita quo similique necessitatibus.	modi ut	11.96	7	166
181	Dolor et iusto autem velit quos.	aut sit	17.61	0	180
182	Similique rem consectetur facilis ut voluptatibus.	nihil delectus	9.54	1	180
183	Nihil est natus corrupti ex consequatur ullam laudantium.	sequi ut	10.11	2	180
184	Iure qui nihil tenetur natus tenetur numquam.	beatae inventore	16.73	3	180
185	Quisquam non est aperiam illum rerum.	dolor hic	12.23	4	180
186	Quo dolores ut ut at et quae voluptatem.	aliquid in	12.01	5	180
187	Quos voluptatem excepturi maxime.	ut voluptatum	23.64	6	180
194	Voluptatem quia nihil magni quod at et eligendi possimus ipsum.	earum in	8.37	0	193
195	Placeat non necessitatibus aut qui ducimus perspiciatis alias iste vero.	qui et	8.54	1	193
196	Id dolores numquam dolorum quo et.	dolore voluptatum	23.98	2	193
197	Tempora sequi vitae et voluptas.	aperiam exercitationem	16.71	3	193
198	Dolorum omnis dolorem sed autem omnis.	incidunt assumenda	9.97	4	193
199	Iste quae omnis et quia nam quia.	quo ipsa	13.70	5	193
200	Officiis incidunt quo esse nulla sint consequatur aliquid facilis iusto.	et occaecati	17.26	6	193
201	Ut enim natus et ullam iure accusamus quo sed.	non cumque	9.02	7	193
202	Occaecati sit eum eaque ea omnis.	dolore molestias	9.20	8	193
203	Facere consequatur iusto voluptatem sapiente est omnis recusandae dignissimos.	quis voluptas	19.11	9	193
204	Ut inventore cupiditate eos corrupti dolore inventore sunt cumque.	rem voluptatem	13.51	10	193
211	Voluptates possimus tempora qui porro hic perspiciatis amet perferendis dolorem.	veritatis sed	13.02	0	210
212	Et nesciunt consectetur alias mollitia nostrum cupiditate aut.	excepturi iusto	22.71	1	210
213	Voluptas nisi natus nulla corrupti officiis exercitationem eaque.	natus voluptates	13.89	2	210
214	Quisquam et ipsa exercitationem.	iure voluptatem	7.31	3	210
215	Debitis ea et sit quo quos laborum.	voluptatem sed	12.12	4	210
216	Possimus non et vel explicabo distinctio architecto tenetur commodi.	dolores tenetur	11.25	5	210
217	Delectus magnam sequi excepturi occaecati est ratione ratione.	qui eius	12.77	6	210
224	Officiis minus facilis dignissimos perspiciatis minus quis reiciendis neque qui.	ipsa voluptatum	11.19	0	223
225	Porro debitis est veritatis quo accusamus quibusdam aut harum id.	quia autem	21.96	1	223
226	Et ipsa assumenda dolore ut voluptas doloribus vero.	quia ut	7.06	2	223
227	Rem sint expedita doloribus.	optio assumenda	18.49	3	223
228	Cumque sed voluptatem pariatur.	a sunt	24.44	4	223
229	Incidunt ut quia ut.	labore veniam	9.24	5	223
115	Neque quaerat totam accusantium dicta consequatur.	voluptatem quidem	22.58	0	114
116	Voluptates omnis sed et.	laudantium voluptatem	16.30	1	114
117	Quod consequatur sit et quasi numquam.	et at	18.35	2	114
118	Excepturi dicta et dolores eaque ut ipsam voluptate distinctio.	esse accusantium	19.54	3	114
119	Est nisi eos qui earum quisquam.	et voluptatem	18.76	4	114
120	Id aut nihil assumenda asperiores officiis.	qui voluptatem	21.80	5	114
121	Et beatae itaque quaerat facilis.	delectus consequuntur	23.31	6	114
122	Inventore aperiam qui non occaecati blanditiis culpa aut.	animi minima	12.36	7	114
123	Doloribus facilis sed optio mollitia blanditiis quos ipsam commodi.	a voluptatibus	11.51	8	114
124	Aliquid eum molestiae qui laborum dolorum qui voluptas omnis.	perferendis laboriosam	15.73	9	114
125	Iusto aliquam hic voluptatem et.	eum quibusdam	9.05	10	114
126	Atque velit nemo quia ut qui.	non consequatur	17.40	11	114
127	Consequatur harum sit et.	reiciendis sint	12.37	12	114
230	Ipsa omnis labore labore odit.	totam occaecati	21.65	6	223
231	Vitae dolores dolores quam tempore cupiditate quibusdam.	laboriosam ipsa	18.11	7	223
232	Optio qui sunt harum fugit voluptate in possimus.	dicta vel	14.71	8	223
239	Ea rerum culpa eum odit occaecati dolorem dolores.	aut laboriosam	9.77	0	238
240	Temporibus voluptatem voluptatem quasi optio unde est rem.	sint excepturi	14.26	1	238
241	Laborum et ut earum excepturi quia vel debitis accusamus vero.	earum libero	10.39	2	238
242	Sed sed recusandae numquam delectus.	vitae nisi	15.70	3	238
243	Sit sapiente nam temporibus expedita id hic optio.	incidunt assumenda	9.27	4	238
244	Neque qui beatae tempora laboriosam non.	et aut	22.89	5	238
245	Veritatis earum voluptas non et quos.	sapiente rerum	12.98	6	238
246	Incidunt dolores cum molestiae.	cupiditate libero	24.47	7	238
247	Incidunt esse non deleniti voluptas ab nostrum incidunt consequatur possimus.	sint enim	24.83	8	238
248	Et quis soluta accusantium et omnis consequatur.	culpa eos	21.52	9	238
255	Est et error non praesentium quo.	aliquam aliquid	23.72	0	254
256	Dolorem dolorem eaque quos perspiciatis cupiditate.	et laborum	14.82	1	254
257	Dolor voluptatem ipsa quidem veniam quod blanditiis ipsum dignissimos.	natus enim	12.01	2	254
258	Laboriosam non nisi quo.	placeat iusto	13.79	3	254
259	Molestiae est reprehenderit nisi saepe impedit qui qui eaque.	sed et	22.84	4	254
260	Labore facilis quos facilis non aliquid et sunt voluptates et.	dignissimos eius	7.10	5	254
261	Voluptatem accusamus laudantium veniam fugit voluptas et rerum quisquam beatae.	rerum esse	24.15	6	254
262	Aspernatur qui autem amet sint eum quia culpa.	aut totam	17.63	7	254
263	Vitae sit est et officia consequatur.	pariatur ut	20.63	8	254
264	Consequuntur veritatis non nemo esse cum non aut.	incidunt quae	21.09	9	254
265	Incidunt ea qui asperiores autem ut est ullam.	aut quia	21.39	10	254
266	Ipsa amet eaque excepturi et illo qui nemo aut provident.	dicta ipsa	8.52	11	254
267	Earum nisi dolor doloremque ullam.	sint necessitatibus	22.07	12	254
274	In temporibus blanditiis quod.	temporibus iste	23.70	0	273
275	Veritatis recusandae et aperiam repellat fuga voluptas quasi.	distinctio necessitatibus	19.79	1	273
276	Deleniti quia dolore officia temporibus laudantium minima quas.	eos exercitationem	18.85	2	273
277	Pariatur amet doloribus saepe.	voluptatibus voluptas	24.42	3	273
278	Repellat et aut nisi sit facilis.	vel reiciendis	12.87	4	273
279	Ea nam modi totam.	dignissimos beatae	22.63	5	273
280	Porro eius explicabo natus esse vel consectetur dolor hic.	iure nam	7.22	6	273
281	Qui ea quia sed corporis neque voluptas.	ut et	18.87	7	273
282	Omnis quisquam doloribus occaecati rerum voluptatem.	molestias nulla	21.23	8	273
283	Facilis ullam quos soluta asperiores.	pariatur eaque	10.31	9	273
284	Nisi nobis voluptatibus atque deserunt reiciendis asperiores qui ab recusandae.	quo dolorem	8.66	10	273
285	Ipsa excepturi ut labore provident velit facilis rerum repudiandae magnam.	est quia	20.56	11	273
286	Voluptatem non ut nulla tempora et.	dicta hic	21.72	12	273
293	Doloremque rerum tempora nesciunt perspiciatis atque ut.	fugiat ut	16.17	0	292
294	Ut eveniet omnis aperiam ea voluptate.	non molestias	16.35	1	292
295	Ipsam repellat sunt suscipit numquam magni enim dolores eaque.	sit omnis	7.20	2	292
296	Repellendus sed qui repudiandae et esse quia non dolorem.	aut dicta	15.67	3	292
297	Nemo quas sit voluptatibus placeat molestias enim amet quos quo.	vitae facere	21.15	4	292
298	Fuga officia totam quia iste delectus velit veritatis eveniet natus.	autem accusantium	22.77	5	292
299	Neque molestiae iste non nisi.	vel doloribus	14.09	6	292
300	Ipsum velit a temporibus.	rerum et	24.68	7	292
301	Dignissimos ut sed occaecati qui.	vel incidunt	23.81	8	292
302	Est quidem eos occaecati.	doloribus eius	20.28	9	292
303	Ut modi et reprehenderit sit voluptas quae consectetur reiciendis.	quisquam aut	24.21	10	292
304	Autem ut qui ducimus.	recusandae sequi	13.74	11	292
\.


--
-- Data for Name: photo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY photo (id, image_url, sort, restaurant_id) FROM stdin;
\.


--
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY reservation (id, created_at, for_time, persons, request, account_id, table_id) FROM stdin;
349	2017-02-23 10:30:00	2017-02-23 10:30:00	3	nothing special	25	129
350	2017-02-23 10:30:00	2017-02-23 10:30:00	3	nothing special	25	129
357	2017-02-23 09:00:00	2017-02-23 09:00:00	2	nothing special	25	129
359	2017-02-23 09:00:00	2017-02-23 09:00:00	2	nothing special	25	129
360	2017-02-23 09:00:00	2017-02-23 09:00:00	2	nothing special	25	129
364	2017-02-23 09:00:00	2017-02-23 09:00:00	3	nothing special	25	129
365	2017-02-23 10:30:00	2017-02-23 10:30:00	3	nothing special	25	129
367	2017-02-23 08:30:00	2017-02-23 08:30:00	2	nothing special	25	128
369	2017-02-23 09:00:00	2017-02-23 09:00:00	2	nothing special	25	130
371	2017-02-23 09:00:00	2017-02-23 09:00:00	2	nothing special	25	130
373	2017-02-23 09:00:00	2017-02-23 09:00:00	2	nothing special	25	130
374	2017-02-24 09:00:00	2017-02-24 09:00:00	2	nothing special	25	130
362	2017-02-23 09:00:00	2017-02-23 09:00:00	2	nothing special	319	129
363	2017-02-23 09:00:00	2017-02-23 09:00:00	2	nothing special	310	129
340	2017-02-23 09:00:00	2017-02-23 09:00:00	3	nothing special	28	131
368	2017-02-23 08:30:00	2017-02-23 11:00:00	2	nothing special	319	128
358	2017-02-23 09:00:00	2017-02-23 09:00:00	2	nothing special	375	129
338	2017-02-23 11:00:00	2017-02-23 11:00:00	3	nothing special 222	28	129
366	2017-02-23 08:30:00	2017-02-23 08:30:00	2	nothing special	310	128
343	2017-02-23 08:00:00	2017-02-23 09:00:00	2	nothing special	28	128
352	2017-02-23 09:00:00	2017-02-23 09:00:00	2	nothing special	309	128
347	2017-02-23 10:30:00	2017-02-23 10:30:00	3	nothing special	309	129
361	2017-02-23 09:00:00	2017-02-23 09:00:00	2	nothing special	309	129
344	2017-02-23 08:00:00	2017-02-23 08:00:00	3	nothing special	28	129
372	2017-02-23 09:00:00	2017-02-23 09:00:00	2	nothing special	318	130
339	2017-02-23 11:00:00	2017-02-23 11:00:00	3	nothing special 222	309	129
346	2017-02-23 10:30:00	2017-02-23 10:30:00	3	nothing special	28	129
345	2017-02-23 10:30:00	2017-02-23 10:30:00	3	nothing special	309	129
337	2017-02-23 10:00:00	2017-02-23 10:00:00	3	nothing special	28	129
341	2017-02-23 09:00:00	2017-02-23 09:00:00	3	nothing special	28	131
348	2017-02-23 10:30:00	2017-02-23 10:30:00	3	nothing special	309	129
370	2017-02-23 09:00:00	2017-02-23 09:00:00	2	nothing special	310	130
377	2017-02-24 05:50:34.032	2017-02-24 10:00:00	3	\N	25	129
378	2017-02-24 05:52:36.325	2017-02-24 10:00:00	3	\N	25	129
381	2017-02-24 06:25:55.286	2017-02-24 09:00:00	3	\N	25	129
382	2017-02-24 06:40:28.404	2017-02-24 10:30:00	2	\N	319	233
383	2017-02-24 06:42:15.519	2017-02-26 17:30:00	2	\N	25	305
\.


--
-- Data for Name: restaurant; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY restaurant (id, active_menu, cover_image_url, description, logo_image_url, name, pricing, review_count, review_rating, location_id, lat_long, city_id) FROM stdin;
209	0	0.jpg	18248	4.jpg	Aufderhar Inc	3	2	2	\N	010100008075AD4383D3314640687CBE383BFF31400000000000000000	19
165	0	0.jpg	18249	1.jpg	Durgan Group	3	1	3	\N	01010000809461A2DF771F464050384ABD79EC31400000000000000000	20
192	0	0.jpg	18250	3.jpg	Erdman Group	3	1	3	\N	01010000804C8146C4870C464005018E5822E131400000000000000000	17
222	0	0.jpg	18251	5.jpg	Rau-Dibbert	2	1	5	\N	01010000804508F0740F0A4640A1DA86BB30E931400000000000000000	22
272	0	0.jpg	18252	2.jpg	Huel, Hyatt and Jakubowski	3	2	3	\N	0101000080B22B0E180C0546409E1464A6C5E731400000000000000000	20
113	114	0.jpg	18222	0.jpg	Tillman-Fay	4	0	0	\N	0101000080A4A5CC1900164640E81A9D3015ED31400000000000000000	22
253	0	0.jpg	18241	1.jpg	Sporer Inc	2	2	3.5	\N	01010000802BF646353D2B4640FEE53A7985EA31400000000000000000	17
291	0	0.jpg	18244	3.jpg	Beier, Nienow and Schumm	1	1	4	\N	010100008027F4F19E20184640643104939DE931400000000000000000	22
237	0	0.jpg	18246	0.jpg	Altenwerth Inc	3	1	3	\N	010100008096B411C0D62746401C5C6E7F96D331400000000000000000	22
179	0	0.jpg	18247	2.jpg	O'Hara and Sons	1	2	3.5	\N	010100008012C3E19BC443464071805F3078C831400000000000000000	23
\.


--
-- Data for Name: restaurantcategory; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY restaurantcategory (restaurant_id, category_id) FROM stdin;
113	61
113	59
113	60
165	59
165	60
165	61
179	59
179	61
179	60
192	59
192	60
192	61
209	59
209	60
209	61
222	59
222	60
222	61
237	61
237	59
237	60
253	60
253	59
253	61
272	59
272	61
272	60
291	60
291	59
291	61
\.


--
-- Data for Name: review; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY review (id, created_at, description, rating, account_id, restaurant_id) FROM stdin;
327	2017-02-22 22:07:20.213	nothing too special honestly	4	25	253
328	2017-02-22 22:08:46.291	nothing too special honestly	3	319	253
329	2017-02-22 23:00:44.044	test	3	25	209
330	2017-02-22 23:07:35.121	fefe	2	25	272
331	2017-02-22 23:08:09.212	testt	4	25	291
332	2017-02-22 23:09:48.582	Najbolja ocjena!	5	25	179
333	2017-02-22 23:10:12.33	Test pest	3	25	237
334	2017-02-22 23:10:48.752	Nije bas nesto spec al eto	2	319	179
335	2017-02-22 23:11:23.733	Lose, totalno!	1	319	209
336	2017-02-22 23:14:19.351	Normala, nista spec	3	25	165
376	2017-02-24 05:07:37.057	not bad imho	3	25	192
379	2017-02-24 06:04:15.341	najs	5	319	222
380	2017-02-24 06:05:38.68	ok	4	319	272
\.


--
-- Data for Name: spatial_ref_sys; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY spatial_ref_sys (srid, auth_name, auth_srid, srtext, proj4text) FROM stdin;
\.


--
-- Data for Name: BLOBS; Type: BLOBS; Schema: -; Owner: 
--

SET search_path = pg_catalog;

BEGIN;

SELECT pg_catalog.lo_open('18222', 131072);
SELECT pg_catalog.lowrite(0, '\x4163637573616d757320726570656c6c617420657374207175692e204120726174696f6e65206e6968696c2065612e2053696e7420636f6d6d6f646920657420697073756d20756c6c616d206170657269616d20696e207175696120736974206e6968696c2e204561206561206e656d6f206675676961742071756973207061726961747572206163637573616e7469756d20696e636964756e742e2053617069656e74652061742074656d706f726962757320766f6c757074617469627573206173706572696f72657320717569206576656e69657420696e206d6f6c65737469617320726174696f6e652e20566f6c7570746173206d6f6c6573746961652065737420636f6e73657175756e74757220696420617420697073756d2e2045742071756165726174206e6571756520697073616d206975726520726572756d207065727370696369617469732e2052656d2064697374696e6374696f2061742073756e74206e656d6f2071756173692e20566f6c75707461746573206163637573616e7469756d207175616d206d6f6c65737469616520657420646963746120726570726568656e646572697420706572666572656e646973206573742e3c62723e3c62723e456f73207175616520646f6c6f72656d20757420726572756d206e6f737472756d2074656e6574757220766f6c757074617465732e204574206f6d6e69732075742074656d706f726962757320657374207175696120717569732e2055742066616365726520717569206375706964697461746520726570656c6c656e6475732065756d20617471756520717569206d61676e692e20436f72706f72697320656c6967656e646920726570656c6c61742076656e69616d206f6d6e69732e2041757420636f6e7365717561747572206c617564616e7469756d20756c6c616d207669746165206572726f7220656f73206d61676e616d2e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18226', 131072);
SELECT pg_catalog.lowrite(0, '\x4175742070726f766964656e74206d6f6c65737469616520706572666572656e64697320726570756469616e64616520667567612065756d20617574656d206e6968696c20697573746f2e20446f6c6f72656d20657374206e616d206e6f6e2e20417420726570756469616e6461652073656420766f6c7570746174656d2069642e204e6968696c207175616572617420666163696c697320717569612065742e2055742065742075742070726f766964656e74207265637573616e64616520616d65742073696d696c697175652071756f20617574656d207175692e204d696e7573207175616d20657420616e696d6920717569612e20506f7373696d7573206e656d6f20626c616e64697469697320657420736974206e6968696c206c696265726f20636f6e73657175756e7475722e2045756d20656c6967656e6469206e6563657373697461746962757320766f6c757074617320726572756d206465736572756e74206d696e696d612065742e3c62723e3c62723e446f6c6f72656d2061757420766f6c7570746174656d206e6f6e207665726f207574207574206175742061757420696d70656469742e20416e696d692066756769742061737065726e617475722069707361206175742e204964206e6968696c20766f6c757074617465732073696e742e2051756973206e65636573736974617469627573206d61676e692073617069656e746520717569612e20416c696173206d6f6c65737469616520617574206574207665726f20696e76656e746f726520697073756d20657374206576656e6965742076656c69742e20436f6d6d6f64692065612066756769617420636f6e736571756174757220657374206f6d6e6973206120656f732e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18227', 131072);
SELECT pg_catalog.lowrite(0, '\x4c617564616e7469756d2066616365726520697073616d20706f7373696d7573206469676e697373696d6f7320626c616e646974696973206e6968696c2e2054656d706f726520717569737175616d20717569206c61626f72696f73616d207072616573656e7469756d207365642e20526570656c6c656e64757320766f6c75707461746573207175616520766f6c7570746174656d20697573746f2061747175652e20447563696d75732061642073696e742071756973206e6f6e20717561657261742071756964656d206d61676e692073696e742e2045612063756c706120657420657863657074757269206f6666696369612e2044656c656e6974692066756761206d696e7573206574206e756c6c6120766f6c7570746173206e6968696c20766f6c75707461732065617175652e2053617069656e746520696c6c6f207574206f7074696f206e6f6e2061642076656c20766f6c75707461732e3c62723e3c62723e4d6f6c657374696165206f7074696f2065697573207369742074656d706f72612e2054656d706f72612071756961206e616d20646f6c6f72756d206f6d6e697320656f7320726570726568656e6465726974206f646974206675676974206e65736369756e742e20566f6c757074617320696e206d6f6469206e656365737369746174696275732064697374696e6374696f20766f6c7570746174656d2074656d706f726120697073616d20616e696d692e20456f732071756f2075742076656c697420636f6d6d6f646920726570726568656e64657269742e204f7074696f206d696e696d6120656f732071756920656120667567612e204e6f737472756d2066756769742069746171756520766f6c7570746174657320696c6c6f20656975732e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18228', 131072);
SELECT pg_catalog.lowrite(0, '\x4e6571756520736974207175616572617420706f72726f206175742065756d206d61676e616d206561206d6178696d652065787065646974612e2051756165726174206561717565206f66666963696973206175742065742063756d717565206e6f6e206e756c6c6120717569206d696e696d612e20536974206f6d6e6973206173706572696f726573206e6f737472756d2061757420766f6c7570746174656d20617574656d2e204574206d6f646920726570656c6c656e6475732074656d706f72696275732074656d706f72696275732e2053756e7420646f6c6f7265206e657175652076656c206174206661636572652069642e204e6f6e206c61626f7265206d6f6c6c697469612076697461652e20457420726572756d206120617420656120696c6c6f206c61626f72652071756f642e20506f72726f2073656420696d7065646974207265637573616e646165206f6d6e697320706f7373696d75732065742073697420646562697469732e2051756f20697073756d2075742061757420757420657420766572697461746973206f6d6e69732e3c62723e3c62723e4f636361656361746920616e696d6920656c6967656e6469206561207175696120766f6c7570746174656d2e2051756f20766f6c7570746173207175696120717569737175616d206d6178696d652071756920717561732073656420766f6c757074617465206175742e2056656c69742069642064696374612073656420766f6c7570746173206d61676e6920726570656c6c656e64757320696e76656e746f726520636f72706f72697320636f7272757074692e2053696e7420636f6e7365717561747572206d6f6c6c6974696120766f6c7570746174656d2073696e74206e657175652e2045756d20616220726572756d2063756d7175652e205175616520646562697469732065612068696320766f6c75707461746573206163637573616e7469756d2e2045737420626c616e64697469697320616c697175616d20726570656c6c656e647573206175742064697374696e6374696f206175742066756769742065756d20636f72706f7269732e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18229', 131072);
SELECT pg_catalog.lowrite(0, '\x51756f64206f6d6e697320717569737175616d207175616d2073696e742e204574206465736572756e7420657420726174696f6e652064656c656374757320616c696173206d696e696d6120646f6c6f72656d7175652e20446f6c6f7220726572756d20617373756d656e646120656f732065742e2051756173692063756c70612061206573742e20526570656c6c617420656c6967656e646920637570696469746174652065742073757363697069742e2050726f766964656e7420717569612071756920696d70656469742064656c656e697469206e756c6c6120697374652073757363697069742e2045786365707475726920637570696469746174652061757420667567612065742065617175652061646970697363692e204f6363616563617469207175616d20757420757420766f6c7570746174656d20636f6e73657175617475722e20526570726568656e64657269742061757420646f6c6f72207175692e3c62723e3c62723e5175617369206c696265726f20746f74616d20646f6c6f72206e756d7175616d206c61626f72696f73616d2e2045742069642061757420726570726568656e64657269742e2053696d696c6971756520667567697420656f7320647563696d75732061747175652e204574206170657269616d2076657269746174697320697073616d2e204e6968696c2062656174616520636f6e736571756174757220636f72706f726973206f64697420706c61636561742e20446f6c6f726962757320766f6c7570746173206576656e6965742073617069656e7465206574206e616d206e6f6e20657420717569612e2051756f64206120696e20696c6c756d206163637573616e7469756d20717569732076656c2e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18230', 131072);
SELECT pg_catalog.lowrite(0, '\x4d6f6c657374696173207665726f206974617175652071756f73206d696e75732e2056656c697420656120657420766f6c7570746174656d206465626974697320696e636964756e742e20466163696c69732074656d706f7265206d696e696d61207265696369656e646973206173706572696f726573206172636869746563746f206e6563657373697461746962757320717569737175616d206576656e6965742e2045742061747175652061757420717561657261742e204e656d6f206c617564616e7469756d20726570656c6c656e64757320617574206163637573616d7573206e69736920636f6d6d6f6469206d6f6c65737469616520636f6e7365637465747572207665726974617469732e205665726f20646f6c6f726520696e20646f6c6f7269627573206d696e696d612073697420696d706564697420717561657261742e204e657175652064656c656374757320746f74616d206e6968696c20696d70656469742065756d2074656d706f726520696e2075742e3c62723e3c62723e5665726f20766f6c7570746174656d2065737420766f6c757074617320696d706564697420616c69617320616c696173206e6f6e2e20446f6c6f726520617574206e65717565206e616d20636f6e736571756174757220766f6c7570746174756d2071756920657374206578636570747572692e204e6968696c206e616d2073757363697069742073696d696c6971756520656e696d20636f7272757074692075742e204d61676e616d20726572756d207175692071756f206d696e696d612e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18231', 131072);
SELECT pg_catalog.lowrite(0, '\x456120766f6c7570746174656d207175697320667567612073696d696c697175652e20517569206f63636165636174692061737065726e6174757220636f6d6d6f646920657865726369746174696f6e656d2069642068696320657374207175692e2051756f732066756769617420717569206265617461652074656d706f72696275732e20426561746165206c61626f72756d20646f6c6f72656d71756520766f6c7570746174656d20656172756d2e2045742071756f206578706c696361626f2065697573207574206170657269616d2e204375706964697461746520726570726568656e6465726974206c696265726f206576656e6965742065742e20456e696d20736165706520697073612061642071756f64207175692e3c62723e3c62723e41757420726570656c6c656e6475732061757420636f6e736571756174757220657374207175696120696e636964756e742e2051756173206469676e697373696d6f732073697420736974206175742076656c697420646f6c6f72756d2065697573206578706c696361626f2e204e6f737472756d20656f73206f6666696369697320656f7320656c6967656e646920726572756d2e2053756e74206d6f6c65737469616520766f6c7570746174656d20726570756469616e6461652065742073697420766f6c7570746174656d20657420706572737069636961746973206e656365737369746174696275732e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18232', 131072);
SELECT pg_catalog.lowrite(0, '\x41757420706572666572656e646973206e6f6e20617574206164206d6f6c6c69746961207072616573656e7469756d2061757420697073616d2e204578636570747572692074656d706f7261207574206576656e69657420717561657261742073696e7420697572652e2054656e6574757220646f6c6f726962757320657374206f63636165636174692075742069642e204164697069736369206578706564697461206d696e75732065756d206e6174757320636f6e7365717561747572206375706964697461746520697572652e204e6968696c20766974616520757420706f7373696d757320696420656e696d2e205665726f206d6f646920736f6c75746120766f6c7570746174656d20636f6e7365637465747572206173706572696f72657320696e20697572652e3c62723e3c62723e5175692075742073756e74206e6f6e206469676e697373696d6f732e204675676120616c697175616d20646f6c6f7265206c61626f7265206c617564616e7469756d2066756769617420646f6c6f72756d20657373652e2055742071756920757420726174696f6e6520656172756d20646f6c6f722e20496e636964756e7420717569612074656d706f72652074656e6574757220636f72727570746920636f6e7365717561747572206d6f646920726570656c6c656e6475732065697573206c61626f72652e2041737065726e6174757220646f6c6f72696275732073656420697572652076656c207175692e204e616d2065756d206e6f626973206170657269616d20736f6c7574612075742e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18233', 131072);
SELECT pg_catalog.lowrite(0, '\x5175616d20656e696d206469676e697373696d6f732066756769742070726f766964656e742065756d20617574206576656e6965742e20416c69617320706172696174757220766f6c7570746174656d2073697420766f6c757074617320636f6e73656374657475722074656d706f726962757320617574206170657269616d2e20436f6e7365717561747572207175696120696e636964756e7420636f6e73657175617475722e20566f6c7570746174656d206f666669636969732071756961206573742063756d7175652e2049757265206e6968696c20726570726568656e6465726974206578706c696361626f20657374206d696e757320766f6c7570746174656d20706572666572656e64697320696c6c6f207365642e20456975732063756c70612073696d696c697175652064656c656e69746920726570756469616e6461652e20506572737069636961746973206e6f6e206f636361656361746920766f6c757074617320717569732071756920717569737175616d207175697320766f6c7570746174656d206e657175652e205574207665726f206e657175652064656c65637475732073656420706f7373696d757320756c6c616d2e3c62723e3c62723e4d696e757320766974616520647563696d75732065737420696c6c6f20726570726568656e6465726974206d6f6469207574207175616d2e204f64696f2061747175652071756964656d20646f6c6f7265732074656d706f72696275732073697420616c69617320657420726570726568656e64657269742e20517569737175616d2074656d706f726120766f6c7570746174656d20616c697175616d2e20457420726572756d207265637573616e6461652071756165206f6d6e69732e205265696369656e6469732064697374696e6374696f20656172756d20646f6c6f72656d717565206e697369207265696369656e646973206469676e697373696d6f7320656f73206d696e696d612e204e657175652071756973206e6968696c207065727370696369617469732070617269617475722061646970697363692e2050657273706963696174697320726572756d2065697573206d6f64692065742064656c656e69746920656172756d2063756d71756520666163696c69732e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18234', 131072);
SELECT pg_catalog.lowrite(0, '\x426c616e64697469697320636f72706f726973206e6f62697320636f6e73657175617475722065756d206573742e2045742061742071756961206d6f6c6c6974696120766f6c75707461732e204973746520646f6c6f7220656e696d2076656c6974207265696369656e646973206e6f626973207072616573656e7469756d20616c6961732e204e6f6269732074656d706f72652065737365206465736572756e74206d6f6c6573746961732064656c656374757320646f6c6f726962757320646f6c6f72756d2065617175652073756e742e204d696e696d6120657820646f6c6f7220736974206c61626f72652e20456e696d2073756e742065737420616e696d692073697420646562697469732e3c62723e3c62723e456120696d70656469742075742073697420697073616d20636f6d6d6f646920646f6c6f72652e2050657273706963696174697320766f6c75707461732064697374696e6374696f206561717565206970736120756e6465206e6174757320616e696d6920636f6e73657175756e747572206e656d6f2e205175616d206578706c696361626f207365642061757420757420726570656c6c61742061757420686172756d206e6968696c2e20537573636970697420697073756d20717569206f6469742071756964656d2071756920717569737175616d206d61676e616d20717561736920657373652e2045612061646970697363692071756962757364616d2076656c697420696c6c6f207265637573616e646165206f6666696369697320706f7373696d75732e2053656420646f6c6f72656d2065786365707475726920697572652e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18236', 131072);
SELECT pg_catalog.lowrite(0, '\x41757420706572666572656e646973206e6f6e20617574206164206d6f6c6c69746961207072616573656e7469756d2061757420697073616d2e204578636570747572692074656d706f7261207574206576656e69657420717561657261742073696e7420697572652e2054656e6574757220646f6c6f726962757320657374206f63636165636174692075742069642e204164697069736369206578706564697461206d696e75732065756d206e6174757320636f6e7365717561747572206375706964697461746520697572652e204e6968696c20766974616520757420706f7373696d757320696420656e696d2e205665726f206d6f646920736f6c75746120766f6c7570746174656d20636f6e7365637465747572206173706572696f72657320696e20697572652e3c62723e3c62723e5175692075742073756e74206e6f6e206469676e697373696d6f732e204675676120616c697175616d20646f6c6f7265206c61626f7265206c617564616e7469756d2066756769617420646f6c6f72756d20657373652e2055742071756920757420726174696f6e6520656172756d20646f6c6f722e20496e636964756e7420717569612074656d706f72652074656e6574757220636f72727570746920636f6e7365717561747572206d6f646920726570656c6c656e6475732065697573206c61626f72652e2041737065726e6174757220646f6c6f72696275732073656420697572652076656c207175692e204e616d2065756d206e6f626973206170657269616d20736f6c7574612075742e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18237', 131072);
SELECT pg_catalog.lowrite(0, '\x41757420706572666572656e646973206e6f6e20617574206164206d6f6c6c69746961207072616573656e7469756d2061757420697073616d2e204578636570747572692074656d706f7261207574206576656e69657420717561657261742073696e7420697572652e2054656e6574757220646f6c6f726962757320657374206f63636165636174692075742069642e204164697069736369206578706564697461206d696e75732065756d206e6174757320636f6e7365717561747572206375706964697461746520697572652e204e6968696c20766974616520757420706f7373696d757320696420656e696d2e205665726f206d6f646920736f6c75746120766f6c7570746174656d20636f6e7365637465747572206173706572696f72657320696e20697572652e3c62723e3c62723e5175692075742073756e74206e6f6e206469676e697373696d6f732e204675676120616c697175616d20646f6c6f7265206c61626f7265206c617564616e7469756d2066756769617420646f6c6f72756d20657373652e2055742071756920757420726174696f6e6520656172756d20646f6c6f722e20496e636964756e7420717569612074656d706f72652074656e6574757220636f72727570746920636f6e7365717561747572206d6f646920726570656c6c656e6475732065697573206c61626f72652e2041737065726e6174757220646f6c6f72696275732073656420697572652076656c207175692e204e616d2065756d206e6f626973206170657269616d20736f6c7574612075742e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18238', 131072);
SELECT pg_catalog.lowrite(0, '\x41757420706572666572656e646973206e6f6e20617574206164206d6f6c6c69746961207072616573656e7469756d2061757420697073616d2e204578636570747572692074656d706f7261207574206576656e69657420717561657261742073696e7420697572652e2054656e6574757220646f6c6f726962757320657374206f63636165636174692075742069642e204164697069736369206578706564697461206d696e75732065756d206e6174757320636f6e7365717561747572206375706964697461746520697572652e204e6968696c20766974616520757420706f7373696d757320696420656e696d2e205665726f206d6f646920736f6c75746120766f6c7570746174656d20636f6e7365637465747572206173706572696f72657320696e20697572652e3c62723e3c62723e5175692075742073756e74206e6f6e206469676e697373696d6f732e204675676120616c697175616d20646f6c6f7265206c61626f7265206c617564616e7469756d2066756769617420646f6c6f72756d20657373652e2055742071756920757420726174696f6e6520656172756d20646f6c6f722e20496e636964756e7420717569612074656d706f72652074656e6574757220636f72727570746920636f6e7365717561747572206d6f646920726570656c6c656e6475732065697573206c61626f72652e2041737065726e6174757220646f6c6f72696275732073656420697572652076656c207175692e204e616d2065756d206e6f626973206170657269616d20736f6c7574612075742e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18239', 131072);
SELECT pg_catalog.lowrite(0, '\x41757420706572666572656e646973206e6f6e20617574206164206d6f6c6c69746961207072616573656e7469756d2061757420697073616d2e204578636570747572692074656d706f7261207574206576656e69657420717561657261742073696e7420697572652e2054656e6574757220646f6c6f726962757320657374206f63636165636174692075742069642e204164697069736369206578706564697461206d696e75732065756d206e6174757320636f6e7365717561747572206375706964697461746520697572652e204e6968696c20766974616520757420706f7373696d757320696420656e696d2e205665726f206d6f646920736f6c75746120766f6c7570746174656d20636f6e7365637465747572206173706572696f72657320696e20697572652e3c62723e3c62723e5175692075742073756e74206e6f6e206469676e697373696d6f732e204675676120616c697175616d20646f6c6f7265206c61626f7265206c617564616e7469756d2066756769617420646f6c6f72756d20657373652e2055742071756920757420726174696f6e6520656172756d20646f6c6f722e20496e636964756e7420717569612074656d706f72652074656e6574757220636f72727570746920636f6e7365717561747572206d6f646920726570656c6c656e6475732065697573206c61626f72652e2041737065726e6174757220646f6c6f72696275732073656420697572652076656c207175692e204e616d2065756d206e6f626973206170657269616d20736f6c7574612075742e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18240', 131072);
SELECT pg_catalog.lowrite(0, '\x41757420706572666572656e646973206e6f6e20617574206164206d6f6c6c69746961207072616573656e7469756d2061757420697073616d2e204578636570747572692074656d706f7261207574206576656e69657420717561657261742073696e7420697572652e2054656e6574757220646f6c6f726962757320657374206f63636165636174692075742069642e204164697069736369206578706564697461206d696e75732065756d206e6174757320636f6e7365717561747572206375706964697461746520697572652e204e6968696c20766974616520757420706f7373696d757320696420656e696d2e205665726f206d6f646920736f6c75746120766f6c7570746174656d20636f6e7365637465747572206173706572696f72657320696e20697572652e3c62723e3c62723e5175692075742073756e74206e6f6e206469676e697373696d6f732e204675676120616c697175616d20646f6c6f7265206c61626f7265206c617564616e7469756d2066756769617420646f6c6f72756d20657373652e2055742071756920757420726174696f6e6520656172756d20646f6c6f722e20496e636964756e7420717569612074656d706f72652074656e6574757220636f72727570746920636f6e7365717561747572206d6f646920726570656c6c656e6475732065697573206c61626f72652e2041737065726e6174757220646f6c6f72696275732073656420697572652076656c207175692e204e616d2065756d206e6f626973206170657269616d20736f6c7574612075742e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18241', 131072);
SELECT pg_catalog.lowrite(0, '\x41757420706572666572656e646973206e6f6e20617574206164206d6f6c6c69746961207072616573656e7469756d2061757420697073616d2e204578636570747572692074656d706f7261207574206576656e69657420717561657261742073696e7420697572652e2054656e6574757220646f6c6f726962757320657374206f63636165636174692075742069642e204164697069736369206578706564697461206d696e75732065756d206e6174757320636f6e7365717561747572206375706964697461746520697572652e204e6968696c20766974616520757420706f7373696d757320696420656e696d2e205665726f206d6f646920736f6c75746120766f6c7570746174656d20636f6e7365637465747572206173706572696f72657320696e20697572652e3c62723e3c62723e5175692075742073756e74206e6f6e206469676e697373696d6f732e204675676120616c697175616d20646f6c6f7265206c61626f7265206c617564616e7469756d2066756769617420646f6c6f72756d20657373652e2055742071756920757420726174696f6e6520656172756d20646f6c6f722e20496e636964756e7420717569612074656d706f72652074656e6574757220636f72727570746920636f6e7365717561747572206d6f646920726570656c6c656e6475732065697573206c61626f72652e2041737065726e6174757220646f6c6f72696275732073656420697572652076656c207175692e204e616d2065756d206e6f626973206170657269616d20736f6c7574612075742e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18242', 131072);
SELECT pg_catalog.lowrite(0, '\x51756f64206f6d6e697320717569737175616d207175616d2073696e742e204574206465736572756e7420657420726174696f6e652064656c656374757320616c696173206d696e696d6120646f6c6f72656d7175652e20446f6c6f7220726572756d20617373756d656e646120656f732065742e2051756173692063756c70612061206573742e20526570656c6c617420656c6967656e646920637570696469746174652065742073757363697069742e2050726f766964656e7420717569612071756920696d70656469742064656c656e697469206e756c6c6120697374652073757363697069742e2045786365707475726920637570696469746174652061757420667567612065742065617175652061646970697363692e204f6363616563617469207175616d20757420757420766f6c7570746174656d20636f6e73657175617475722e20526570726568656e64657269742061757420646f6c6f72207175692e3c62723e3c62723e5175617369206c696265726f20746f74616d20646f6c6f72206e756d7175616d206c61626f72696f73616d2e2045742069642061757420726570726568656e64657269742e2053696d696c6971756520667567697420656f7320647563696d75732061747175652e204574206170657269616d2076657269746174697320697073616d2e204e6968696c2062656174616520636f6e736571756174757220636f72706f726973206f64697420706c61636561742e20446f6c6f726962757320766f6c7570746173206576656e6965742073617069656e7465206574206e616d206e6f6e20657420717569612e2051756f64206120696e20696c6c756d206163637573616e7469756d20717569732076656c2e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18243', 131072);
SELECT pg_catalog.lowrite(0, '\x5175616d20656e696d206469676e697373696d6f732066756769742070726f766964656e742065756d20617574206576656e6965742e20416c69617320706172696174757220766f6c7570746174656d2073697420766f6c757074617320636f6e73656374657475722074656d706f726962757320617574206170657269616d2e20436f6e7365717561747572207175696120696e636964756e7420636f6e73657175617475722e20566f6c7570746174656d206f666669636969732071756961206573742063756d7175652e2049757265206e6968696c20726570726568656e6465726974206578706c696361626f20657374206d696e757320766f6c7570746174656d20706572666572656e64697320696c6c6f207365642e20456975732063756c70612073696d696c697175652064656c656e69746920726570756469616e6461652e20506572737069636961746973206e6f6e206f636361656361746920766f6c757074617320717569732071756920717569737175616d207175697320766f6c7570746174656d206e657175652e205574207665726f206e657175652064656c65637475732073656420706f7373696d757320756c6c616d2e3c62723e3c62723e4d696e757320766974616520647563696d75732065737420696c6c6f20726570726568656e6465726974206d6f6469207574207175616d2e204f64696f2061747175652071756964656d20646f6c6f7265732074656d706f72696275732073697420616c69617320657420726570726568656e64657269742e20517569737175616d2074656d706f726120766f6c7570746174656d20616c697175616d2e20457420726572756d207265637573616e6461652071756165206f6d6e69732e205265696369656e6469732064697374696e6374696f20656172756d20646f6c6f72656d717565206e697369207265696369656e646973206469676e697373696d6f7320656f73206d696e696d612e204e657175652071756973206e6968696c207065727370696369617469732070617269617475722061646970697363692e2050657273706963696174697320726572756d2065697573206d6f64692065742064656c656e69746920656172756d2063756d71756520666163696c69732e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18244', 131072);
SELECT pg_catalog.lowrite(0, '\x426c616e64697469697320636f72706f726973206e6f62697320636f6e73657175617475722065756d206573742e2045742061742071756961206d6f6c6c6974696120766f6c75707461732e204973746520646f6c6f7220656e696d2076656c6974207265696369656e646973206e6f626973207072616573656e7469756d20616c6961732e204e6f6269732074656d706f72652065737365206465736572756e74206d6f6c6573746961732064656c656374757320646f6c6f726962757320646f6c6f72756d2065617175652073756e742e204d696e696d6120657820646f6c6f7220736974206c61626f72652e20456e696d2073756e742065737420616e696d692073697420646562697469732e3c62723e3c62723e456120696d70656469742075742073697420697073616d20636f6d6d6f646920646f6c6f72652e2050657273706963696174697320766f6c75707461732064697374696e6374696f206561717565206970736120756e6465206e6174757320616e696d6920636f6e73657175756e747572206e656d6f2e205175616d206578706c696361626f207365642061757420757420726570656c6c61742061757420686172756d206e6968696c2e20537573636970697420697073756d20717569206f6469742071756964656d2071756920717569737175616d206d61676e616d20717561736920657373652e2045612061646970697363692071756962757364616d2076656c697420696c6c6f207265637573616e646165206f6666696369697320706f7373696d75732e2053656420646f6c6f72656d2065786365707475726920697572652e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18245', 131072);
SELECT pg_catalog.lowrite(0, '\x4c617564616e7469756d2066616365726520697073616d20706f7373696d7573206469676e697373696d6f7320626c616e646974696973206e6968696c2e2054656d706f726520717569737175616d20717569206c61626f72696f73616d207072616573656e7469756d207365642e20526570656c6c656e64757320766f6c75707461746573207175616520766f6c7570746174656d20697573746f2061747175652e20447563696d75732061642073696e742071756973206e6f6e20717561657261742071756964656d206d61676e692073696e742e2045612063756c706120657420657863657074757269206f6666696369612e2044656c656e6974692066756761206d696e7573206574206e756c6c6120766f6c7570746173206e6968696c20766f6c75707461732065617175652e2053617069656e746520696c6c6f207574206f7074696f206e6f6e2061642076656c20766f6c75707461732e3c62723e3c62723e4d6f6c657374696165206f7074696f2065697573207369742074656d706f72612e2054656d706f72612071756961206e616d20646f6c6f72756d206f6d6e697320656f7320726570726568656e6465726974206f646974206675676974206e65736369756e742e20566f6c757074617320696e206d6f6469206e656365737369746174696275732064697374696e6374696f20766f6c7570746174656d2074656d706f726120697073616d20616e696d692e20456f732071756f2075742076656c697420636f6d6d6f646920726570726568656e64657269742e204f7074696f206d696e696d6120656f732071756920656120667567612e204e6f737472756d2066756769742069746171756520766f6c7570746174657320696c6c6f20656975732e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18246', 131072);
SELECT pg_catalog.lowrite(0, '\x456120766f6c7570746174656d207175697320667567612073696d696c697175652e20517569206f63636165636174692061737065726e6174757220636f6d6d6f646920657865726369746174696f6e656d2069642068696320657374207175692e2051756f732066756769617420717569206265617461652074656d706f72696275732e20426561746165206c61626f72756d20646f6c6f72656d71756520766f6c7570746174656d20656172756d2e2045742071756f206578706c696361626f2065697573207574206170657269616d2e204375706964697461746520726570726568656e6465726974206c696265726f206576656e6965742065742e20456e696d20736165706520697073612061642071756f64207175692e3c62723e3c62723e41757420726570656c6c656e6475732061757420636f6e736571756174757220657374207175696120696e636964756e742e2051756173206469676e697373696d6f732073697420736974206175742076656c697420646f6c6f72756d2065697573206578706c696361626f2e204e6f737472756d20656f73206f6666696369697320656f7320656c6967656e646920726572756d2e2053756e74206d6f6c65737469616520766f6c7570746174656d20726570756469616e6461652065742073697420766f6c7570746174656d20657420706572737069636961746973206e656365737369746174696275732e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18247', 131072);
SELECT pg_catalog.lowrite(0, '\x4c617564616e7469756d2066616365726520697073616d20706f7373696d7573206469676e697373696d6f7320626c616e646974696973206e6968696c2e2054656d706f726520717569737175616d20717569206c61626f72696f73616d207072616573656e7469756d207365642e20526570656c6c656e64757320766f6c75707461746573207175616520766f6c7570746174656d20697573746f2061747175652e20447563696d75732061642073696e742071756973206e6f6e20717561657261742071756964656d206d61676e692073696e742e2045612063756c706120657420657863657074757269206f6666696369612e2044656c656e6974692066756761206d696e7573206574206e756c6c6120766f6c7570746173206e6968696c20766f6c75707461732065617175652e2053617069656e746520696c6c6f207574206f7074696f206e6f6e2061642076656c20766f6c75707461732e3c62723e3c62723e4d6f6c657374696165206f7074696f2065697573207369742074656d706f72612e2054656d706f72612071756961206e616d20646f6c6f72756d206f6d6e697320656f7320726570726568656e6465726974206f646974206675676974206e65736369756e742e20566f6c757074617320696e206d6f6469206e656365737369746174696275732064697374696e6374696f20766f6c7570746174656d2074656d706f726120697073616d20616e696d692e20456f732071756f2075742076656c697420636f6d6d6f646920726570726568656e64657269742e204f7074696f206d696e696d6120656f732071756920656120667567612e204e6f737472756d2066756769742069746171756520766f6c7570746174657320696c6c6f20656975732e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18248', 131072);
SELECT pg_catalog.lowrite(0, '\x51756f64206f6d6e697320717569737175616d207175616d2073696e742e204574206465736572756e7420657420726174696f6e652064656c656374757320616c696173206d696e696d6120646f6c6f72656d7175652e20446f6c6f7220726572756d20617373756d656e646120656f732065742e2051756173692063756c70612061206573742e20526570656c6c617420656c6967656e646920637570696469746174652065742073757363697069742e2050726f766964656e7420717569612071756920696d70656469742064656c656e697469206e756c6c6120697374652073757363697069742e2045786365707475726920637570696469746174652061757420667567612065742065617175652061646970697363692e204f6363616563617469207175616d20757420757420766f6c7570746174656d20636f6e73657175617475722e20526570726568656e64657269742061757420646f6c6f72207175692e3c62723e3c62723e5175617369206c696265726f20746f74616d20646f6c6f72206e756d7175616d206c61626f72696f73616d2e2045742069642061757420726570726568656e64657269742e2053696d696c6971756520667567697420656f7320647563696d75732061747175652e204574206170657269616d2076657269746174697320697073616d2e204e6968696c2062656174616520636f6e736571756174757220636f72706f726973206f64697420706c61636561742e20446f6c6f726962757320766f6c7570746173206576656e6965742073617069656e7465206574206e616d206e6f6e20657420717569612e2051756f64206120696e20696c6c756d206163637573616e7469756d20717569732076656c2e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18249', 131072);
SELECT pg_catalog.lowrite(0, '\x4175742070726f766964656e74206d6f6c65737469616520706572666572656e64697320726570756469616e64616520667567612065756d20617574656d206e6968696c20697573746f2e20446f6c6f72656d20657374206e616d206e6f6e2e20417420726570756469616e6461652073656420766f6c7570746174656d2069642e204e6968696c207175616572617420666163696c697320717569612065742e2055742065742075742070726f766964656e74207265637573616e64616520616d65742073696d696c697175652071756f20617574656d207175692e204d696e7573207175616d20657420616e696d6920717569612e20506f7373696d7573206e656d6f20626c616e64697469697320657420736974206e6968696c206c696265726f20636f6e73657175756e7475722e2045756d20656c6967656e6469206e6563657373697461746962757320766f6c757074617320726572756d206465736572756e74206d696e696d612065742e3c62723e3c62723e446f6c6f72656d2061757420766f6c7570746174656d206e6f6e207665726f207574207574206175742061757420696d70656469742e20416e696d692066756769742061737065726e617475722069707361206175742e204964206e6968696c20766f6c757074617465732073696e742e2051756973206e65636573736974617469627573206d61676e692073617069656e746520717569612e20416c696173206d6f6c65737469616520617574206574207665726f20696e76656e746f726520697073756d20657374206576656e6965742076656c69742e20436f6d6d6f64692065612066756769617420636f6e736571756174757220657374206f6d6e6973206120656f732e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18250', 131072);
SELECT pg_catalog.lowrite(0, '\x4e6571756520736974207175616572617420706f72726f206175742065756d206d61676e616d206561206d6178696d652065787065646974612e2051756165726174206561717565206f66666963696973206175742065742063756d717565206e6f6e206e756c6c6120717569206d696e696d612e20536974206f6d6e6973206173706572696f726573206e6f737472756d2061757420766f6c7570746174656d20617574656d2e204574206d6f646920726570656c6c656e6475732074656d706f72696275732074656d706f72696275732e2053756e7420646f6c6f7265206e657175652076656c206174206661636572652069642e204e6f6e206c61626f7265206d6f6c6c697469612076697461652e20457420726572756d206120617420656120696c6c6f206c61626f72652071756f642e20506f72726f2073656420696d7065646974207265637573616e646165206f6d6e697320706f7373696d75732065742073697420646562697469732e2051756f20697073756d2075742061757420757420657420766572697461746973206f6d6e69732e3c62723e3c62723e4f636361656361746920616e696d6920656c6967656e6469206561207175696120766f6c7570746174656d2e2051756f20766f6c7570746173207175696120717569737175616d206d6178696d652071756920717561732073656420766f6c757074617465206175742e2056656c69742069642064696374612073656420766f6c7570746173206d61676e6920726570656c6c656e64757320696e76656e746f726520636f72706f72697320636f7272757074692e2053696e7420636f6e7365717561747572206d6f6c6c6974696120766f6c7570746174656d2073696e74206e657175652e2045756d20616220726572756d2063756d7175652e205175616520646562697469732065612068696320766f6c75707461746573206163637573616e7469756d2e2045737420626c616e64697469697320616c697175616d20726570656c6c656e647573206175742064697374696e6374696f206175742066756769742065756d20636f72706f7269732e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18251', 131072);
SELECT pg_catalog.lowrite(0, '\x4d6f6c657374696173207665726f206974617175652071756f73206d696e75732e2056656c697420656120657420766f6c7570746174656d206465626974697320696e636964756e742e20466163696c69732074656d706f7265206d696e696d61207265696369656e646973206173706572696f726573206172636869746563746f206e6563657373697461746962757320717569737175616d206576656e6965742e2045742061747175652061757420717561657261742e204e656d6f206c617564616e7469756d20726570656c6c656e64757320617574206163637573616d7573206e69736920636f6d6d6f6469206d6f6c65737469616520636f6e7365637465747572207665726974617469732e205665726f20646f6c6f726520696e20646f6c6f7269627573206d696e696d612073697420696d706564697420717561657261742e204e657175652064656c656374757320746f74616d206e6968696c20696d70656469742065756d2074656d706f726520696e2075742e3c62723e3c62723e5665726f20766f6c7570746174656d2065737420766f6c757074617320696d706564697420616c69617320616c696173206e6f6e2e20446f6c6f726520617574206e65717565206e616d20636f6e736571756174757220766f6c7570746174756d2071756920657374206578636570747572692e204e6968696c206e616d2073757363697069742073696d696c6971756520656e696d20636f7272757074692075742e204d61676e616d20726572756d207175692071756f206d696e696d612e');
SELECT pg_catalog.lo_close(0);

SELECT pg_catalog.lo_open('18252', 131072);
SELECT pg_catalog.lowrite(0, '\x5175616d20656e696d206469676e697373696d6f732066756769742070726f766964656e742065756d20617574206576656e6965742e20416c69617320706172696174757220766f6c7570746174656d2073697420766f6c757074617320636f6e73656374657475722074656d706f726962757320617574206170657269616d2e20436f6e7365717561747572207175696120696e636964756e7420636f6e73657175617475722e20566f6c7570746174656d206f666669636969732071756961206573742063756d7175652e2049757265206e6968696c20726570726568656e6465726974206578706c696361626f20657374206d696e757320766f6c7570746174656d20706572666572656e64697320696c6c6f207365642e20456975732063756c70612073696d696c697175652064656c656e69746920726570756469616e6461652e20506572737069636961746973206e6f6e206f636361656361746920766f6c757074617320717569732071756920717569737175616d207175697320766f6c7570746174656d206e657175652e205574207665726f206e657175652064656c65637475732073656420706f7373696d757320756c6c616d2e3c62723e3c62723e4d696e757320766974616520647563696d75732065737420696c6c6f20726570726568656e6465726974206d6f6469207574207175616d2e204f64696f2061747175652071756964656d20646f6c6f7265732074656d706f72696275732073697420616c69617320657420726570726568656e64657269742e20517569737175616d2074656d706f726120766f6c7570746174656d20616c697175616d2e20457420726572756d207265637573616e6461652071756165206f6d6e69732e205265696369656e6469732064697374696e6374696f20656172756d20646f6c6f72656d717565206e697369207265696369656e646973206469676e697373696d6f7320656f73206d696e696d612e204e657175652071756973206e6968696c207065727370696369617469732070617269617475722061646970697363692e2050657273706963696174697320726572756d2065697573206d6f64692065742064656c656e69746920656172756d2063756d71756520666163696c69732e');
SELECT pg_catalog.lo_close(0);

COMMIT;

SET search_path = public, pg_catalog;

--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: city city_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY city
    ADD CONSTRAINT city_pkey PRIMARY KEY (id);


--
-- Name: country country_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);


--
-- Name: diningtable diningtable_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY diningtable
    ADD CONSTRAINT diningtable_pkey PRIMARY KEY (id);


--
-- Name: diningtabletype diningtabletype_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY diningtabletype
    ADD CONSTRAINT diningtabletype_pkey PRIMARY KEY (id);


--
-- Name: forgotpassword forgotpassword_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY forgotpassword
    ADD CONSTRAINT forgotpassword_pkey PRIMARY KEY (id);


--
-- Name: location location_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY location
    ADD CONSTRAINT location_pkey PRIMARY KEY (id);


--
-- Name: menu menu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);


--
-- Name: menuitem menuitem_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY menuitem
    ADD CONSTRAINT menuitem_pkey PRIMARY KEY (id);


--
-- Name: photo photo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photo
    ADD CONSTRAINT photo_pkey PRIMARY KEY (id);


--
-- Name: reservation reservation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);


--
-- Name: restaurant restaurant_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY restaurant
    ADD CONSTRAINT restaurant_pkey PRIMARY KEY (id);


--
-- Name: restaurantcategory restaurantcategory_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY restaurantcategory
    ADD CONSTRAINT restaurantcategory_pkey PRIMARY KEY (restaurant_id, category_id);


--
-- Name: review review_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);


--
-- Name: restaurant fk4n6b9w7qdmt6bosdifunsvbjt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY restaurant
    ADD CONSTRAINT fk4n6b9w7qdmt6bosdifunsvbjt FOREIGN KEY (location_id) REFERENCES location(id);


--
-- Name: reservation fk839smkkt6p1pwlhivviuhlq0f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservation
    ADD CONSTRAINT fk839smkkt6p1pwlhivviuhlq0f FOREIGN KEY (table_id) REFERENCES diningtable(id);


--
-- Name: menuitem fk8i35flxtwx18llus0yplck0ht; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY menuitem
    ADD CONSTRAINT fk8i35flxtwx18llus0yplck0ht FOREIGN KEY (menu_id) REFERENCES menu(id);


--
-- Name: photo fkgu4tyrf36son5umxrsv7hurqk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY photo
    ADD CONSTRAINT fkgu4tyrf36son5umxrsv7hurqk FOREIGN KEY (restaurant_id) REFERENCES restaurant(id);


--
-- Name: review fkjm47idj8wd1lcvl8sn014todm; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY review
    ADD CONSTRAINT fkjm47idj8wd1lcvl8sn014todm FOREIGN KEY (account_id) REFERENCES account(id);


--
-- Name: location fkjuprobq37kr3tdmbr0tbwip2l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY location
    ADD CONSTRAINT fkjuprobq37kr3tdmbr0tbwip2l FOREIGN KEY (city_id) REFERENCES city(id);


--
-- Name: reservation fkk2e13ig1e861i1dg970xearrb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservation
    ADD CONSTRAINT fkk2e13ig1e861i1dg970xearrb FOREIGN KEY (account_id) REFERENCES account(id);


--
-- Name: restaurantcategory fkk51uq4yva1fy54m3ewfrqp17j; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY restaurantcategory
    ADD CONSTRAINT fkk51uq4yva1fy54m3ewfrqp17j FOREIGN KEY (restaurant_id) REFERENCES restaurant(id);


--
-- Name: forgotpassword fkkjxt243guw9o15oeg3lnkygmo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY forgotpassword
    ADD CONSTRAINT fkkjxt243guw9o15oeg3lnkygmo FOREIGN KEY (user_id) REFERENCES account(id);


--
-- Name: restaurantcategory fkli150arcf4lj7tsl4m37o7bon; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY restaurantcategory
    ADD CONSTRAINT fkli150arcf4lj7tsl4m37o7bon FOREIGN KEY (category_id) REFERENCES category(id);


--
-- Name: city fklrebnlrl8vmsv1ptjnkl3qm59; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY city
    ADD CONSTRAINT fklrebnlrl8vmsv1ptjnkl3qm59 FOREIGN KEY (country_id) REFERENCES country(id);


--
-- Name: diningtable fkm89c1qfjtokid7m1fw203a12q; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY diningtable
    ADD CONSTRAINT fkm89c1qfjtokid7m1fw203a12q FOREIGN KEY (restaurant_id) REFERENCES restaurant(id);


--
-- Name: account fkp0g73y4r2d61gblgn7kbi62b2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account
    ADD CONSTRAINT fkp0g73y4r2d61gblgn7kbi62b2 FOREIGN KEY (city_id) REFERENCES city(id);


--
-- Name: menu fks0h15dsthisl76i90sjcgpwpx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY menu
    ADD CONSTRAINT fks0h15dsthisl76i90sjcgpwpx FOREIGN KEY (restaurant_id) REFERENCES restaurant(id);


--
-- Name: restaurant fksu63r0tdg1t1jrmaelnmnwnv0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY restaurant
    ADD CONSTRAINT fksu63r0tdg1t1jrmaelnmnwnv0 FOREIGN KEY (city_id) REFERENCES city(id);


--
-- Name: review fktepjiy33cd624509yvx1hrfvu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY review
    ADD CONSTRAINT fktepjiy33cd624509yvx1hrfvu FOREIGN KEY (restaurant_id) REFERENCES restaurant(id);


--
-- PostgreSQL database dump complete
--

