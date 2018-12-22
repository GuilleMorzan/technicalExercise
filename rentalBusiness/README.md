Considerations:
	-  If the user returns the bike late, a fine will be charged.
 	-  I assume that users make correct decisions. For example, if they want to rent a bike for 6 hours they choose to rent it per day, since it costs them cheaper.
 	- The user management system is out of scope.

Brief explanation about the design:
	- The domain's classes are: Customer, Bike and RentalCompany.
	- Customer: knows how much money they have and the list of rented bikes. Its responsibilities are to pay, receive the bikes that the rental company gives and return them.
	- Bike: knows its serial number, when it has to be returned and the rental company to which it belongs. It has no responsibility.
	- RentalCompany: knows the money they have, and the bicycles they have available to rent. Their responsibilities are to charge the Customer, apply a discount in case it is a family plan, deliver the bikes, receive them when the user returns them, and charge a fine in case they return them out of time.
	 - The logic of renting per hour, day or week is done through the RentalStrategy, this is an abstract class that has the responsibility to rent. It is extended by the following classes: RentalByHourStrategy, RentalByDayStrategy and RentalByWeekStrategy which override the rent method. The strategies are responsible for setting the return date of the bike and calculate how much the customer has to pay.
	- There are 3 business exceptions: BadRequestException (which is thrown in case the parameters sent to a method are invalid), InsufficientFundsException (which is thrown in case the Customer has to pay but does not have enough money) and NotAvailableBikesException (which is thrown in case a customer wants to rent a bike but the rental company does not have any available).
	- Finally there are 3 utilitarian classes: ApplicationProperties (its responsibility is to access the file application.properties), LoggerUtils (is responsible for configuring the logger), and TimeUtils (with only one method which adds hours to the current date).

Development practices applied:
	- I used Findbugs and EclEmma. In a real job I would have used Sonar.
Small commits.
	- I worked on the branch master. In a real job I would have used a feature branch.
	- In some methods I developed using TDD, but in others I first implemented the logic and, later on, its tests.
	- I missed doing peer programming or peer review.

How to run tests:
	- Install maven.
	- Go to the rentalBusiness folder.
	- Execute the "mvn test" command.

