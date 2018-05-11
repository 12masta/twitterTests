# Twitter tests for Netguru - enjoy :)

## Setup

Chrome browser installed.

## Organization

A specification is represented as a Groovy class that extends from com.ms.twittertests.BaseSpec. The name of a 
specification relates to tested functionality.

You can find tests in: 

    \twitterTests\src\test\groovy\com\ms\twittertests

## What features are tested and why?
### Tweet
Tweet, because it is all around a tweets.
### Like
Basic social media core feature. Tweeter is a social media portal so I am decide to test it.

## Finally, how to run tests?
### Windows:
Run from project root directory:

    gradlew clean test
    
### Mac:
Run from project root directory:

    bash gradlew clean test

clean command is not mandatory, but if you want to rerun tests and you don't add it gradle will detect that nothing was 
changed and did not execute tests.

### Comments from author
Tested on Windows and Mac.