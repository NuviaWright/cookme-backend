It is a simple website where you search for recipes on ingredients in your fridge.

This is a spring boot project. Run the server using the ff. command
1. If you're running this for the first time:
```
mvn clean install
mvn spring-boot:run
```
2. If not, you can run the server using `mvn spring-boot:run`.

The developer is using a third-party API called [TheMealDB](https://www.themealdb.com/) to get all the recipes and ingredients. The project is not yet complete because the developer is thinking if he will subscribe for the additional features that he clearly needs.

Future features:

1. A recipe result for multiple ingredients
2. Limit the result to ingredients added to the list
