--
-- PostgreSQL database dump
--

-- Dumped from database version 16.4 (Debian 16.4-1.pgdg120+1)
-- Dumped by pg_dump version 16.4

-- Started on 2024-11-07 15:12:15 UTC

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 32891)
-- Name: accept_list; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.accept_list (
    event_id bigint,
    id bigint NOT NULL,
    student_id bigint
);


ALTER TABLE public.accept_list OWNER TO "user";

--
-- TOC entry 217 (class 1259 OID 32890)
-- Name: accept_list_id_seq; Type: SEQUENCE; Schema: public; Owner: user
--

ALTER TABLE public.accept_list ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.accept_list_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 219 (class 1259 OID 32898)
-- Name: accept_list_student_list; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.accept_list_student_list (
    appcept_id bigint NOT NULL,
    student_list_id bigint NOT NULL
);


ALTER TABLE public.accept_list_student_list OWNER TO "user";

--
-- TOC entry 221 (class 1259 OID 32904)
-- Name: community_leader; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.community_leader (
    event_id bigint,
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    address character varying(255),
    phone character varying(255)
);


ALTER TABLE public.community_leader OWNER TO "user";

--
-- TOC entry 220 (class 1259 OID 32903)
-- Name: community_leader_id_seq; Type: SEQUENCE; Schema: public; Owner: user
--

ALTER TABLE public.community_leader ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.community_leader_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 223 (class 1259 OID 32914)
-- Name: event; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.event (
    number_of_people integer NOT NULL,
    status smallint,
    accept_id bigint,
    end_time timestamp(6) without time zone,
    id bigint NOT NULL,
    start_time timestamp(6) without time zone,
    description character varying(255),
    event_name character varying(255),
    location character varying(255),
    university_id bigint,
    CONSTRAINT event_status_check CHECK (((status >= 0) AND (status <= 2)))
);


ALTER TABLE public.event OWNER TO "user";

--
-- TOC entry 222 (class 1259 OID 32913)
-- Name: event_id_seq; Type: SEQUENCE; Schema: public; Owner: user
--

ALTER TABLE public.event ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 216 (class 1259 OID 25040)
-- Name: local; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.local (
    id bigint NOT NULL,
    local_address character varying(255),
    local_name character varying(255)
);


ALTER TABLE public.local OWNER TO "user";

--
-- TOC entry 215 (class 1259 OID 25039)
-- Name: local_id_seq; Type: SEQUENCE; Schema: public; Owner: user
--

ALTER TABLE public.local ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.local_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 225 (class 1259 OID 32925)
-- Name: student; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.student (
    id bigint NOT NULL,
    university_id bigint,
    user_id bigint NOT NULL,
    address character varying(255),
    mssv character varying(255),
    event bigint
);


ALTER TABLE public.student OWNER TO "user";

--
-- TOC entry 224 (class 1259 OID 32924)
-- Name: student_id_seq; Type: SEQUENCE; Schema: public; Owner: user
--

ALTER TABLE public.student ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.student_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 227 (class 1259 OID 32933)
-- Name: university; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.university (
    id bigint NOT NULL,
    university_name character varying(255) NOT NULL
);


ALTER TABLE public.university OWNER TO "user";

--
-- TOC entry 226 (class 1259 OID 32932)
-- Name: university_id_seq; Type: SEQUENCE; Schema: public; Owner: user
--

ALTER TABLE public.university ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.university_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 229 (class 1259 OID 32939)
-- Name: university_staff; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.university_staff (
    id bigint NOT NULL,
    university_id bigint,
    user_id bigint NOT NULL
);


ALTER TABLE public.university_staff OWNER TO "user";

--
-- TOC entry 228 (class 1259 OID 32938)
-- Name: university_staff_id_seq; Type: SEQUENCE; Schema: public; Owner: user
--

ALTER TABLE public.university_staff ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.university_staff_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 231 (class 1259 OID 32947)
-- Name: user_; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.user_ (
    role smallint,
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    full_name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    phone_number character varying(255),
    CONSTRAINT user__role_check CHECK (((role >= 0) AND (role <= 2)))
);


ALTER TABLE public.user_ OWNER TO "user";

--
-- TOC entry 230 (class 1259 OID 32946)
-- Name: user__id_seq; Type: SEQUENCE; Schema: public; Owner: user
--

ALTER TABLE public.user_ ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.user__id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3433 (class 0 OID 32891)
-- Dependencies: 218
-- Data for Name: accept_list; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.accept_list (event_id, id, student_id) FROM stdin;
\.


--
-- TOC entry 3434 (class 0 OID 32898)
-- Dependencies: 219
-- Data for Name: accept_list_student_list; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.accept_list_student_list (appcept_id, student_list_id) FROM stdin;
\.


--
-- TOC entry 3436 (class 0 OID 32904)
-- Dependencies: 221
-- Data for Name: community_leader; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.community_leader (event_id, id, user_id, address, phone) FROM stdin;
\.


--
-- TOC entry 3438 (class 0 OID 32914)
-- Dependencies: 223
-- Data for Name: event; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.event (number_of_people, status, accept_id, end_time, id, start_time, description, event_name, location, university_id) FROM stdin;
300	\N	\N	2024-12-15 17:00:00	1	2024-12-15 09:00:00	A conference on the latest in tech innovation, AI, and robotics.	Technology Conference 2024	TechPark Convention Center, Hanoi	1
\.


--
-- TOC entry 3431 (class 0 OID 25040)
-- Dependencies: 216
-- Data for Name: local; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.local (id, local_address, local_name) FROM stdin;
\.


--
-- TOC entry 3440 (class 0 OID 32925)
-- Dependencies: 225
-- Data for Name: student; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.student (id, university_id, user_id, address, mssv, event) FROM stdin;
1	1	1	so 1 Nguyen Van Ngan, District 1	\N	\N
2	1	2	so 1 Nguyen Van Ngan, District 1	\N	\N
3	1	3	so 1 Nguyen Van Ngan, District 1	\N	\N
\.


--
-- TOC entry 3442 (class 0 OID 32933)
-- Dependencies: 227
-- Data for Name: university; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.university (id, university_name) FROM stdin;
1	HCMUT
2	UIT
3	VNUF
4	NEU
\.


--
-- TOC entry 3444 (class 0 OID 32939)
-- Dependencies: 229
-- Data for Name: university_staff; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.university_staff (id, university_id, user_id) FROM stdin;
\.


--
-- TOC entry 3446 (class 0 OID 32947)
-- Dependencies: 231
-- Data for Name: user_; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.user_ (role, id, email, full_name, password, phone_number) FROM stdin;
0	1	student@example.com	Nguyen Van A	$2a$10$RSs/i.p56dSPvEAmF5UyxOWs/IAUGsm2yci/QMLnR5Nijzr7tB312	0123456789
0	2	student1@example.com	Nguyen Van B	$2a$10$X7gcPHW2UyEjgVNVBs/uXOFB7ScbEfCEmedy.yqIu9voSDNVqowRC	0123456789
0	3	student2@example.com	Nguyen Van C	$2a$10$QB5t0GNo9wDp9OLtuFRF0utxa9aNYTXV/x4dHmwnhiDK4s2J3uFvi	0123456789
\.


--
-- TOC entry 3452 (class 0 OID 0)
-- Dependencies: 217
-- Name: accept_list_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.accept_list_id_seq', 1, false);


--
-- TOC entry 3453 (class 0 OID 0)
-- Dependencies: 220
-- Name: community_leader_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.community_leader_id_seq', 1, false);


--
-- TOC entry 3454 (class 0 OID 0)
-- Dependencies: 222
-- Name: event_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.event_id_seq', 1, true);


--
-- TOC entry 3455 (class 0 OID 0)
-- Dependencies: 215
-- Name: local_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.local_id_seq', 1, false);


--
-- TOC entry 3456 (class 0 OID 0)
-- Dependencies: 224
-- Name: student_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.student_id_seq', 3, true);


--
-- TOC entry 3457 (class 0 OID 0)
-- Dependencies: 226
-- Name: university_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.university_id_seq', 4, true);


--
-- TOC entry 3458 (class 0 OID 0)
-- Dependencies: 228
-- Name: university_staff_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.university_staff_id_seq', 1, false);


--
-- TOC entry 3459 (class 0 OID 0)
-- Dependencies: 230
-- Name: user__id_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.user__id_seq', 3, true);


--
-- TOC entry 3247 (class 2606 OID 32897)
-- Name: accept_list accept_list_event_id_key; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.accept_list
    ADD CONSTRAINT accept_list_event_id_key UNIQUE (event_id);


--
-- TOC entry 3249 (class 2606 OID 32895)
-- Name: accept_list accept_list_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.accept_list
    ADD CONSTRAINT accept_list_pkey PRIMARY KEY (id);


--
-- TOC entry 3251 (class 2606 OID 32902)
-- Name: accept_list_student_list accept_list_student_list_student_list_id_key; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.accept_list_student_list
    ADD CONSTRAINT accept_list_student_list_student_list_id_key UNIQUE (student_list_id);


--
-- TOC entry 3253 (class 2606 OID 32910)
-- Name: community_leader community_leader_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.community_leader
    ADD CONSTRAINT community_leader_pkey PRIMARY KEY (id);


--
-- TOC entry 3255 (class 2606 OID 32912)
-- Name: community_leader community_leader_user_id_key; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.community_leader
    ADD CONSTRAINT community_leader_user_id_key UNIQUE (user_id);


--
-- TOC entry 3257 (class 2606 OID 32923)
-- Name: event event_accept_id_key; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_accept_id_key UNIQUE (accept_id);


--
-- TOC entry 3259 (class 2606 OID 32921)
-- Name: event event_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT event_pkey PRIMARY KEY (id);


--
-- TOC entry 3245 (class 2606 OID 25046)
-- Name: local local_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.local
    ADD CONSTRAINT local_pkey PRIMARY KEY (id);


--
-- TOC entry 3261 (class 2606 OID 32929)
-- Name: student student_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (id);


--
-- TOC entry 3263 (class 2606 OID 32931)
-- Name: student student_user_id_key; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_user_id_key UNIQUE (user_id);


--
-- TOC entry 3265 (class 2606 OID 32937)
-- Name: university university_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.university
    ADD CONSTRAINT university_pkey PRIMARY KEY (id);


--
-- TOC entry 3267 (class 2606 OID 32943)
-- Name: university_staff university_staff_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.university_staff
    ADD CONSTRAINT university_staff_pkey PRIMARY KEY (id);


--
-- TOC entry 3269 (class 2606 OID 32945)
-- Name: university_staff university_staff_user_id_key; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.university_staff
    ADD CONSTRAINT university_staff_user_id_key UNIQUE (user_id);


--
-- TOC entry 3271 (class 2606 OID 32956)
-- Name: user_ user__email_key; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.user_
    ADD CONSTRAINT user__email_key UNIQUE (email);


--
-- TOC entry 3273 (class 2606 OID 32954)
-- Name: user_ user__pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.user_
    ADD CONSTRAINT user__pkey PRIMARY KEY (id);


--
-- TOC entry 3282 (class 2606 OID 32987)
-- Name: student fk157t7rer269uuhfphq1mcd7y9; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fk157t7rer269uuhfphq1mcd7y9 FOREIGN KEY (university_id) REFERENCES public.university(id);


--
-- TOC entry 3278 (class 2606 OID 32977)
-- Name: community_leader fk5udq4sg7ds408d22l88q6hbn1; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.community_leader
    ADD CONSTRAINT fk5udq4sg7ds408d22l88q6hbn1 FOREIGN KEY (user_id) REFERENCES public.user_(id);


--
-- TOC entry 3283 (class 2606 OID 33019)
-- Name: student fk7twunlpgpw0mkj2rv6c2gar2o; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fk7twunlpgpw0mkj2rv6c2gar2o FOREIGN KEY (event) REFERENCES public.event(id);


--
-- TOC entry 3284 (class 2606 OID 32992)
-- Name: student fk9xte7n7xxo7qpx4jxsqqsrr8m; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.student
    ADD CONSTRAINT fk9xte7n7xxo7qpx4jxsqqsrr8m FOREIGN KEY (user_id) REFERENCES public.user_(id);


--
-- TOC entry 3285 (class 2606 OID 32997)
-- Name: university_staff fkauadimpmnj9wni3t1vj19drl4; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.university_staff
    ADD CONSTRAINT fkauadimpmnj9wni3t1vj19drl4 FOREIGN KEY (university_id) REFERENCES public.university(id);


--
-- TOC entry 3276 (class 2606 OID 32962)
-- Name: accept_list_student_list fkl3nyquqxg165m47ok33uy62vs; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.accept_list_student_list
    ADD CONSTRAINT fkl3nyquqxg165m47ok33uy62vs FOREIGN KEY (student_list_id) REFERENCES public.student(id);


--
-- TOC entry 3277 (class 2606 OID 32967)
-- Name: accept_list_student_list fklfs8wh31u1g1peoc7sg8yl7qc; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.accept_list_student_list
    ADD CONSTRAINT fklfs8wh31u1g1peoc7sg8yl7qc FOREIGN KEY (appcept_id) REFERENCES public.accept_list(id);


--
-- TOC entry 3279 (class 2606 OID 32972)
-- Name: community_leader fkmjdu4vi7n72897jgd6kiurgjn; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.community_leader
    ADD CONSTRAINT fkmjdu4vi7n72897jgd6kiurgjn FOREIGN KEY (event_id) REFERENCES public.event(id);


--
-- TOC entry 3274 (class 2606 OID 33009)
-- Name: accept_list fkmjk1v0onqeyuj130klcnauvvp; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.accept_list
    ADD CONSTRAINT fkmjk1v0onqeyuj130klcnauvvp FOREIGN KEY (student_id) REFERENCES public.student(id);


--
-- TOC entry 3275 (class 2606 OID 32957)
-- Name: accept_list fknejdugnmpfrri3o03wbj89nvw; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.accept_list
    ADD CONSTRAINT fknejdugnmpfrri3o03wbj89nvw FOREIGN KEY (event_id) REFERENCES public.event(id);


--
-- TOC entry 3280 (class 2606 OID 32982)
-- Name: event fknelc1ttqdnlu6pkm6dhssxm8t; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT fknelc1ttqdnlu6pkm6dhssxm8t FOREIGN KEY (accept_id) REFERENCES public.accept_list(id);


--
-- TOC entry 3281 (class 2606 OID 33014)
-- Name: event fknli4ti1kp6cuu2xf4hnp9fgra; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.event
    ADD CONSTRAINT fknli4ti1kp6cuu2xf4hnp9fgra FOREIGN KEY (university_id) REFERENCES public.university(id);


--
-- TOC entry 3286 (class 2606 OID 33002)
-- Name: university_staff fkrv3tu29p03re9xcfisdij4lwd; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.university_staff
    ADD CONSTRAINT fkrv3tu29p03re9xcfisdij4lwd FOREIGN KEY (user_id) REFERENCES public.user_(id);


-- Completed on 2024-11-07 15:12:16 UTC

--
-- PostgreSQL database dump complete
--
