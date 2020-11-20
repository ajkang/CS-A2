# CS-A2

YouTube video of project running: https://www.youtube.com/watch?v=PgM0hJwJVz0

Project uses the design patterns: Singleton, Composite, Observer, and Visitor.

Singleton: The singleton design pattern is used in Admin in order to provide a private static instance of Admin, so there isn't multiple instances of Admin being used in other places of code.

Composite: The composite design pattern is used with the User and UserGroup class in order to make the design pattern heirarchy. The User is the leaf in the pattern, and both classes use SysEntry as the parent class.

Observer: The observer design pattern is used for keeping track of the followings and tweets that are seen by followers. When the a user follows someone, the user following is on the other user's observer list, and when a tweet is sent, this updates anyone following the user.

*Note that Composite and Observer pattern were combined in terms of the hierarchy and uses

Visitor: The visitor design pattern is used in the buttons for the classes GroupTotal, MessageTotal, UserTotal, and PositiveMessageTotal and other visitor classes like SysEntryVisitable, SysEntryVisitor. The visitor pattern is used for UserGroup to create the hierarchy of items.

Given extension by Professor Sun
