# JTheque XML Utils #

This library provide some utility classes to read and write XML files.

## Building ##

You need a Java 6 (or newer) environment and Maven 2.0.9 (or newer) installed. You also need to have installed
this library into your maven repository :
[jtheque-utils](http://github.com/wichtounet/jtheque-utils "jtheque-utils")

You should now be able to do a full build of `jtheque-xml-utils`:

    $ git clone git://github.com/wichtounet/jtheque-xml-utils.git
    $ cd jtheque-xml-utils
    $ mvn clean install

To use this library in your projects, add the following to the `dependencies` section of your `pom.xml`:

    <dependency>
       <groupId>org.jtheque</groupId>
       <artifactId>org.jtheque.xml.utils</artifactId>
       <version>1.0.0-SNAPSHOT</version>
    </dependency>

## Troubleshooting ##

Please consider using [Github issues tracker](http://github.com/wichtounet/jtheque-xml-utils/issues)
to submit bug reports or feature requests.

## License ##

See `LICENSE` for details.