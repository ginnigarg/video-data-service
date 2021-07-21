--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3
-- Dumped by pg_dump version 13.3
--
-- Name: thumbnail_video; Type: TABLE; Schema: public; Owner: guneetginnigarg
--

CREATE TABLE public.thumbnail_video (
    id bigint NOT NULL,
    size character varying(255),
    url character varying(255),
    video_id bigint,
    created_at timestamp without time zone,
    updated_at timestamp without time zone
);

--
-- Name: thumbnail_video_id_seq; Type: SEQUENCE; Schema: public; Owner: guneetginnigarg
--

CREATE SEQUENCE public.thumbnail_video_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: thumbnail_video_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: guneetginnigarg
--

ALTER SEQUENCE public.thumbnail_video_id_seq OWNED BY public.thumbnail_video.id;


--
-- Name: video; Type: TABLE; Schema: public; Owner: guneetginnigarg
--

CREATE TABLE public.video (
    id bigint NOT NULL,
    description character varying(255),
    publishing_time bytea,
    title character varying(255),
    created_at timestamp without time zone,
    updated_at timestamp without time zone
);

--
-- Name: video_id_seq; Type: SEQUENCE; Schema: public; Owner: guneetginnigarg
--

CREATE SEQUENCE public.video_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- Name: video_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: guneetginnigarg
--

ALTER SEQUENCE public.video_id_seq OWNED BY public.video.id;


--
-- Name: video_thumbnails; Type: TABLE; Schema: public; Owner: guneetginnigarg
--

CREATE TABLE public.video_thumbnails (
    video_data_entity_id bigint NOT NULL,
    thumbnails_id bigint NOT NULL
);

--
-- Name: thumbnail_video id; Type: DEFAULT; Schema: public; Owner: guneetginnigarg
--

ALTER TABLE ONLY public.thumbnail_video ALTER COLUMN id SET DEFAULT nextval('public.thumbnail_video_id_seq'::regclass);


--
-- Name: video id; Type: DEFAULT; Schema: public; Owner: guneetginnigarg
--

ALTER TABLE ONLY public.video ALTER COLUMN id SET DEFAULT nextval('public.video_id_seq'::regclass);


--
-- Name: thumbnail_video thumbnail_video_pkey; Type: CONSTRAINT; Schema: public; Owner: guneetginnigarg
--

ALTER TABLE ONLY public.thumbnail_video
    ADD CONSTRAINT thumbnail_video_pkey PRIMARY KEY (id);


--
-- Name: video_thumbnails uk_j2mqi31xyv12mvj4i9198vhw7; Type: CONSTRAINT; Schema: public; Owner: guneetginnigarg
--

ALTER TABLE ONLY public.video_thumbnails
    ADD CONSTRAINT uk_j2mqi31xyv12mvj4i9198vhw7 UNIQUE (thumbnails_id);


--
-- Name: video video_pkey; Type: CONSTRAINT; Schema: public; Owner: guneetginnigarg
--

ALTER TABLE ONLY public.video
    ADD CONSTRAINT video_pkey PRIMARY KEY (id);


--
-- Name: video_thumbnails fk1af77y5vuqjnlt0se4s86qv4m; Type: FK CONSTRAINT; Schema: public; Owner: guneetginnigarg
--

ALTER TABLE ONLY public.video_thumbnails
    ADD CONSTRAINT fk1af77y5vuqjnlt0se4s86qv4m FOREIGN KEY (video_data_entity_id) REFERENCES public.video(id);


--
-- Name: thumbnail_video fk2kgpbrrvwhtjcpicsegh4lw4i; Type: FK CONSTRAINT; Schema: public; Owner: guneetginnigarg
--

ALTER TABLE ONLY public.thumbnail_video
    ADD CONSTRAINT fk2kgpbrrvwhtjcpicsegh4lw4i FOREIGN KEY (video_id) REFERENCES public.video(id);


--
-- Name: video_thumbnails fkdil5csi3oq3xjrwgh9oi97ql; Type: FK CONSTRAINT; Schema: public; Owner: guneetginnigarg
--

ALTER TABLE ONLY public.video_thumbnails
    ADD CONSTRAINT fkdil5csi3oq3xjrwgh9oi97ql FOREIGN KEY (thumbnails_id) REFERENCES public.thumbnail_video(id);


--
-- PostgreSQL database dump complete
--