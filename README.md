# BurpHolder - Variables For BurpSuite
BurpHolder allows you to define variables inside BurpSuite.

The plugin will make your penetration testing work much easier when investigating complex applications.

For example, if you have a repeated payload that you use, or some certain string that needs to be present in your packets, you can use the plugin to make your work easier, for example:

```ini
placeholder_name = string
```

And then you can access & place your variable (placeholder) in ***ANY REQUEST WINDOW OF BURP***, this way:

````
...index.php?param=[[placeholder_name]]...
```

As you can see, all you have to do, is to write the placeholder name surrounded with `[[` and `]]`.

A better use case of this plugin, is when you test a multi-role platform and need to use several sessions, for example:

```ini
sessid_admin  = fbade9e36a3f36d3d676c1b808451dd7
sessid_user   = d6cbb48444bf8cf4e6460eebceaefce1
sessid_viewer = 15b6046b8cf7ae8ff64265d7e4d0eea2
```

Comments can be placed in any form, for example:

```ini
id = 325790 // comment
//comment
and even this is a comment :)
```

## Version
Current version is an alpha version, future versions will contain better GUI and much more functionality.

## How it looks

![alt tag](https://raw.githubusercontent.com/kasif-dekel/BurpHolder/master/how_it_looks.png)

## Difference between BurpSuite's "Match And Replace"
BurpHolder purports to be more convenient and easier to deal with.

Plus, As mentioned, BurpHolder's placeholders (variables) will work on any request window of burp, including but not limited to:


* Scanner
* Intruder
* Repeater
* Proxy
* Etc..

BurpHolder's next versions will be even more flexibles and with much more functionality.

## Lets see it in action
Lets test BurpHolder with a little PHP script i wrote 

```php
<?php 


echo "GET/POST Parameters:".PHP_EOL;
var_dump($_REQUEST); // $_GET, $_POST

echo "Cookies Parameters:".PHP_EOL;
var_dump($_COOKIE); // on newer versions of PHP the $_REQUEST array does not contain cookie inputs

?>
```

This script should print any $_GET, $_POST, $_COOKIE parameters sent to the server.

For the test, lets assume we have the following BurpHolder variables:

```ini
var1 = 1
var2 = 2
var3 = 3

```

A request would be: 

![alt tag](https://raw.githubusercontent.com/kasif-dekel/BurpHolder/master/example_request.png)

And the response:

![alt tag](https://raw.githubusercontent.com/kasif-dekel/BurpHolder/master/example_response.png)

