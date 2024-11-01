#City Seekers
## Architecture
The current architecture for the project is Clean Architecture, following the best practices to
divide the responsibilities and making it more easy to maintain and test.
Using different layers to make the different transformations from data models, business to filter
the algorithms and filters, and the presentation layer to make ui transformations with navigation in
compose.

## Algorithms
For the first charge, make a call to gist to load the Cities. Then insert all of this data
into room database. With this, only load the information from a local repository.
For the order algorithms, using the room properties and optimizations in sql tables to maximize it
using the sentence Order by name Asc. With this it can be delegated all of the responsibility to SQL.
For the filter algorithm, after load the list, in the use case a map is applied that filters the city
names with the proper prefix that starts with the query.
For the favorites, in the room table a new value is added (isFavorite) to validate and change the
value in local repository

## Thought Process
At the beginning, before coding, reading the criteria and details is the main part of the planning
step. Knowing all of the details, I opted to implement clean architecture to make it more readable,
using libraries as hilt, room, retrofit and google maps are vitals to make the app as the minimum
required. After this implementation, the UI using compose and the navigation using safe args is the
best way to proceed to this. For the algorithms, knowing the best practices and the functions
definitions, help me planning what tools am I going to use on this app
