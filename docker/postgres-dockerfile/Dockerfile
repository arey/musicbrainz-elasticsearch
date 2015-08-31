FROM postgres:9.4

RUN apt-get update

RUN DEBIAN_FRONTEND=noninteractive apt-get -y -q install git-core build-essential libxml2-dev libpq-dev libexpat1-dev libdb-dev libicu-dev postgresql-server-dev-9.4 wget

RUN git clone https://github.com/metabrainz/postgresql-musicbrainz-unaccent.git && git clone https://github.com/metabrainz/postgresql-musicbrainz-collate.git

RUN cd postgresql-musicbrainz-unaccent && make && make install && cd ../postgresql-musicbrainz-collate && make && make install && cd ../

RUN echo "listen_addresses='*'" >> /var/lib/postgresql/data/postgresql.conf

ADD create-database.sh /create-database.sh
