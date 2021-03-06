PGDMP     .    *                y            bank    13.0    13.0     ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    17286    bank    DATABASE     a   CREATE DATABASE bank WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';
    DROP DATABASE bank;
                postgres    false            ?            1259    25447    accounts    TABLE     ?   CREATE TABLE public.accounts (
    id integer NOT NULL,
    name character varying,
    surname character varying,
    phone character varying,
    bank integer,
    login character varying,
    password character varying
);
    DROP TABLE public.accounts;
       public         heap    postgres    false            ?            1259    25445    accounts_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.accounts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.accounts_id_seq;
       public          postgres    false    201            ?           0    0    accounts_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.accounts_id_seq OWNED BY public.accounts.id;
          public          postgres    false    200            ?            1259    25460    transactions    TABLE        CREATE TABLE public.transactions (
    id integer NOT NULL,
    sender_id integer,
    receiver_id integer,
    sum integer
);
     DROP TABLE public.transactions;
       public         heap    postgres    false            ?            1259    25458    transactions_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.transactions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.transactions_id_seq;
       public          postgres    false    203            ?           0    0    transactions_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.transactions_id_seq OWNED BY public.transactions.id;
          public          postgres    false    202            *           2604    25450    accounts id    DEFAULT     j   ALTER TABLE ONLY public.accounts ALTER COLUMN id SET DEFAULT nextval('public.accounts_id_seq'::regclass);
 :   ALTER TABLE public.accounts ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    201    200    201            +           2604    25463    transactions id    DEFAULT     r   ALTER TABLE ONLY public.transactions ALTER COLUMN id SET DEFAULT nextval('public.transactions_id_seq'::regclass);
 >   ALTER TABLE public.transactions ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    202    203    203            ?          0    25447    accounts 
   TABLE DATA           S   COPY public.accounts (id, name, surname, phone, bank, login, password) FROM stdin;
    public          postgres    false    201   3       ?          0    25460    transactions 
   TABLE DATA           G   COPY public.transactions (id, sender_id, receiver_id, sum) FROM stdin;
    public          postgres    false    203   ?       ?           0    0    accounts_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.accounts_id_seq', 3, true);
          public          postgres    false    200            ?           0    0    transactions_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.transactions_id_seq', 12, true);
          public          postgres    false    202            -           2606    25455    accounts accounts_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.accounts DROP CONSTRAINT accounts_pkey;
       public            postgres    false    201            ?   p   x?E??
?0Dϓ?)?&!??????
"?4??b???'O3oa?U??c??8@?a?,+?c?.?>?X??r ?i?!"?wR?߲???m??bH9??`?????BI??s?e#      ?   6   x??? 0??]L?A??%???kG? 8d???4Dѓ?V-???_??$`p     