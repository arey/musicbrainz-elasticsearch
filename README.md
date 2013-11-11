# MusicBrainz Elasticsearch #

Like freedb, [MusicBrainz](http://musicbrainz.org/ "MusicBrainz Home") is an open music encyclopedia that collects music metadata and makes it available to the public.

The **musicbrainz-elasticsearch** project is a java **batch** that **indexes release groups** of the MusicBrainz database into an **Elasticsearch** index.<br/>
From release groups, only "real" **Album** are indexed. Single, EP and Broadcast are not indexed. And from Album release group primary type, neither Compilation, Live, Remix or Soundtrack secondary types are indexed.

## Features ##

* **SQL request** selecting music album from the MusicBrainz PostgreSQL datanase 
* Elasticsearch **index settings** and **mapping** of the musicalbum index in **JSON** format
* Tasklet deleting previous index
* Tasklets creating settings and mappings for the musicalbum index
* **Parallel ES indexation** using multi-threads on a single process
* A **java main** class to launch the batch (through command line, IDE or maven)
* End-to-end **unit tests** with U2 discography

## Powered by ##

This project depends on several other open source projects:

* **[Spring Batch](http://projects.spring.io/spring-batch/ "Spring Batch Home")** : the most popular Java batch framework.
* **[Spring Elasticsearch](https://github.com/dadoonet/spring-elasticsearch "Spring factories for Elasticsearch")** : Spring factories for Elasticsearch used to inject ES client into batch tasklets.
* **[MusicBrainz data](https://github.com/lastfm/musicbrainz-data "musicbrainz-data")** : useful [Gender](https://github.com/lastfm/musicbrainz-data/blob/musicbrainz-data-3.1.0/src/main/java/fm/last/musicbrainz/data/model/Gender.java "Gender.java"), [ReleaseGroupPrimaryType](https://github.com/lastfm/musicbrainz-data/blob/musicbrainz-data-3.1.0/src/main/java/fm/last/musicbrainz/data/model/ReleaseGroupPrimaryType.java "ReleaseGroupPrimaryType.java") and [ArtistType](https://github.com/lastfm/musicbrainz-data/blob/musicbrainz-data-3.1.0/src/main/java/fm/last/musicbrainz/data/model/ArtistType.java "ArtistType.java") enumerations. JPA data bindings is not used by the batch.
* **[DbSetup](http://dbsetup.ninja-squad.com/)** : the database unit test framework you must try.

## Prerequisites ##

### 1. MusicBrainz ###

To index MusicBrainz data, **the batch requires a connection to the MusicBrainz PostgreSQL relational database**.<br>
[Musicbrainz.org](http://Musicbrainz.org "Musicbrainz.org") does not provide a public access to its database. Thus you have to install your own database.
There are a two different methods to get a local database up and running, you can either:

* Download a pre-configured [virtual image of the MusicBrainz Server](http://musicbrainz.org/doc/MusicBrainz_Server/Setup), or
* Download the last [data dumps](http://ftp.musicbrainz.org/pub/musicbrainz/data/fullexport/) and follow the relevant section of the [INSTALL.md](https://github.com/metabrainz/musicbrainz-server/blob/master/INSTALL.md)

For my part, I have chosen to download the MusicBrainz Server virtual machine. Available in Open Virtualization Archive (OVA), I have deployed it into [Oracle VirtualBox](https://www.virtualbox.org/) but you may prefer VMWare.<br/>
Once finished the [MusicBrainz Server setup guide](http://musicbrainz.org/doc/MusicBrainz_Server/Setup), you have to follow the below two final steps in order the PostgreSQL database be accessible to your host:

1. **Configuring port forwarding with NAT**<br/>
Port forwarding enables VirtualBox to listen to certain ports on the host and resends all packets which arrive there to the guest, on the same or a different port. You may used same port on host and guest. Configure two rules (the second is optional): 

- PostgreSQL database - TCP - host : 5432 / guest : 5432 
- MusicBrainz web server : TCP - host : 5000 / guest : 5000

2. **Configuring PostgreSQL**<br />
To enable  remote access to the PostgreSQL database server, you may follow [those instructions](http://www.cyberciti.biz/tips/postgres-allow-remote-access-tcp-connection.html "How Do I Enable remote access to PostgreSQL database server?"). Log into the VM (credentials: vm / musicbrainz) and edit the two configuration files pg_hba.conf and postgresql.conf.

Once steps done, you may connect to the database with any JDBC clients (ie. [SQuireL](http://squirrel-sql.sourceforge.net/ "SQuirreL SQL Client")): 

* URL: jdbc:postgresql://localhost:5432/musicbrainz
* Credentials: musicbrainz / musicbrainz

### 2. Elasticsearch ###

Before launching the batch, you have to [download Elasticsearch v0.90.5](http://www.elasticsearch.org/download/) and configure it. 
One unziped, edit the config/elaticsearch.yml configuration file. Uncomment the _cluster.name_ line and set it with the  _musicbrainz_ cluster name:
`cluster.name: musicbrainz`
You may also prefer to keep the default _elasticsearch_ cluster name and change the name in the es-musicbrainz-batch.properties configuration file.

## Quick Start ##

* `git clone https://github.com/arey/musicbrainz-elasticsearch.git`
* start Elasticsearch
* start MusicBrainz database or VM
* `mvn install`
* `mvn exec:java` (execute the *IndexBatchMain* main class) 

## Demo

![My Demo Screenshot](https://raw.github.com/arey/musicbrainz-elasticsearch/gh-pages/img/musicbrainz-elasticsearch-gui.png "My Demo Screenshot")


## Contributing to MusicBrainz Elasticsearch project ##

* Github is for social coding platform: if you want to write code, we encourage contributions through pull requests from [forks of this repository](http://help.github.com/forking/). If you want to contribute code this way, please reference a GitHub ticket as well covering the specific issue you are addressing.

### Development environment installation ###

Download the code with git:
git clone git://github.com/arey/musicbrainz-elasticsearch.git

Compile the code with maven:


`mvn clean install`

If you're using an IDE that supports Maven-based projects (InteliJ Idea, Netbeans or m2Eclipse), you can import the project directly from its POM. 
Otherwise, generate IDE metadata with the related IDE maven plugin:

`mvn eclipse:clean eclipse:eclipse`

## Documentation ##

French articles on the [javaetmoi.com](http://javaetmoi.com) blog:

* [Parallélisation de traitements batchs](http://javaetmoi.com/2012/12/parallelisation-de-traitements-batchs/)


## Release Note ##

<table>
  <tr>
    <th>Version</th><th>Release date</th><th>Features date</th>
  </tr>
  <tr>
    <td>1.0</td><td>26/10/2013</td><td>Initial version developed for a workshop  about Elasticsearch</td>
  </tr>
</table>

## Credits ##

* Uses [Maven](http://maven.apache.org/) as a build tool
* Uses [Cloudbees](http://www.cloudbees.com/foss) and [Travis CI](www.travis-ci.org) for continuous integration builds whenever code is pushed into GitHub
* Authors of all used open source librairies

## Build Status ##

Travis : [![Build
Status](https://travis-ci.org/arey/musicbrainz-elasticsearch.png?branch=master)](https://travis-ci.org/arey/musicbrainz-elasticsearch)

Cloudbees Jenkins : [![Build
Status](https://javaetmoi.ci.cloudbees.com/job/musicbrainz-elasticsearch/badge/icon)](https://javaetmoi.ci.cloudbees.com/job/musicbrainz-elasticsearch/)
