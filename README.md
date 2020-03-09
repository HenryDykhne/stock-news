# STOCK NEWS
## Notes:
* The `run` method in the `stockNews.App` class is intended only for demonstration for m1 and thus breaks single responsibility.
* In order to use persistance to store infomation between runs, please use the save/load profile and save/load stock commands.
* The directory called `testFiles` contains files that can be tested with and provide the template for how to build new data.

## Data Structure(Schema included in own document):
### Blacklists:
* csv file
```
blacklistName1,untrustworthysite1.com,untrustworthysite2.com,word...
blacklistName2,untrustworthysite3.ca...
...
```

### Stocks:
* csv file
```
stockCode1,searchTerm1,searchTerm2
stockCode2,searchTerm3,searchTerm4
...
```

## Setup and running instructions:
1. Open terminal.
2. Go to the project directory.
3. Run the following commands in terminal to build the project: 
```
gradle build
```
4. Run the following commands in terminal to run the project:
```
cd build
cd libs
java -jar StockNews-1.0-SNAPSHOT.jar 
```
### Changes Made Regarding M1 Marking Suggestions for M2:
* \# Of acceptance criteria has been increased for new stories where possible.
* setBlacklists, addBlacklists and various other methods already implemented in the abstract `actor` class have been removed from the `Admin` subclass.
* StockNews class has been renamed to `App` to better reflect its use.
* Packages including `roles`, `ioUtilities` and `news` have been added to increase organization. `newsPojo` has been seated inside the `news` package.
* Various bugfixes.

### Liskov Substitution:
* The only abstract class within my project is specifically designed to break liskov substitution in the case of methods that only the Admin or user are supposed to have access to. In this case, it allows all roles to be swapped in and out easily with room for extension. When an `Actor` attempts something they do not have priveleges for, the program informs the programmer automatically via exceptions.
* As for interfaces, the `CSVImporter` extends the `DataImporter` and fulfills the expectations of its methods in precisely the way intended. At no point is potential unexpected behavior introduced. As such it is built to be easily swapped in and out with other data importer's.
### Interface Segregation:
* The `DataImporter` interface contains only the functions needed to be implemented to fulfill its purpose. With no waste, it is a good example of interface segregation.

### Test Coverage:
The test coverage in this project was intended to cover methods, excluding getters, setters and other similar one function bits of code. Main lines were tested on most methods. Alternative error lines that throw exceptions were also tested on the `Actor` hierarchy both the `Stock` and `Blacklist` classes. Classes that had to do with saving and loading do have some tests, but they are commented out because they have difficulty running on the online linting pipeline.
## User Stories M2

## User Story 1:
#### Story:
As a Businessman, I want to be able to add an item to an existing blacklist to modify my filters.
#### Acceptance Criteria:
>Scenario: The Businessman wants to add `badSite.com` to `obviousTestBlacklist`.
>
>Given the user has the `obviousTestBlacklist` loaded and has logged in,
>
>When the user enters the command `add to blacklist`,
>
>Then the program will prompt for a blacklist name,
>
>Given the user has provided a valid blacklist name,
>
>When the user enters it,
>
>Then the program will prompt for text to add to the list,
>
>Given the user has provided text,
>
>When the user enters it,
>
>Then the selected blacklist will have the entered text added to its list of restricted text (testable with the `show blacklist` command)
-------------------
>Scenario: The Businessman wants to add `badSite.com` to `obviousTestBlacklist`.
>
>Given the user has logged in,
>
>When the user enters the command `add to blacklist`,
>
>Then the program will prompt for a blacklist name,
>
>Given the user has provided an invalid blacklist name,
>
>When the user enters it,
>
>The program will prompt for text to add.
>
>Then the program will inform the user that it is unable to find the selected blacklist.

## User Story 2:
#### Story:
As a Businessman, I want to be able to remove an item from an existing blacklist to modify my filters.
#### Acceptance Criteria:
>Scenario: The Businessman wants to remove `badSite.com` to `obviousTestBlacklist`.
>
>Given the user has the `obviousTestBlacklist` loaded and has logged in,
>
>When the user enters the command `remove from blacklist`,
>
>Then the program will prompt for a blacklist name,
>
>Given the user has provided a valid blacklist name,
>
>When the user enters it,
>
>Then the program will prompt for text to add to the list,
>
>Given the user has provided text,
>
>When the user enters it,
>
>Then the selected blacklist will have the entered text removed from its list of restricted text (testable with the `show blacklist` command)
-------------------
>Scenario: The Businessman wants to add `badSite.com` to `obviousTestBlacklist`.
>
>Given the user has logged in,
>
>When the user enters the command `remove from blacklist`,
>
>Then the program will prompt for a blacklist name,
>
>Given the user has provided an invalid blacklist name,
>
>When the user enters it,
>
>The program will prompt for text to remove.
>
>Then the program will inform the user that it is unable to find the selected blacklist.

### User Story 3:
#### Story:
As a Businessman, I want to be able to activate a blacklist so that I can more accurately filter my news.
#### Acceptance Criteria:
>Scenario: The Businessman wants to activate a blacklist named: `obviousTestBlacklist`.
>
>Given the user has the `obviousTestBlacklist` loaded and has logged in,
>
>When the user enters the command `activate blacklist`,
>
>Then the program will prompt for a blacklist name,
>
>Given the user has provided a valid blacklist name,
>
>When the user enters it,
>
>Then the selected blacklist will have its active value switched to true (checkable by using `show blacklist` command)
--------------------
>Scenario: The Businessman wants to activate a blacklist that does not exist
>
>Given the user has logged in,
>
>When the user enters the command `activate blacklist`,
>
>Then the program will prompt for a blacklist name,
>
>Given the user has provided an invalid blacklist name,
>
>When the user enters it,
>
>Then program will inform the user that it is unable to find the selected blacklist.

### User Story 4:
#### Story:
As a Businessman, I want to be able to deactivate a blacklist so that I can more accurately filter my news.
#### Acceptance Criteria:
>Scenario: The Businessman wants to deactivate a blacklist named: `obviousTestBlacklist`.
>
>Given the user has the `obviousTestBlacklist` loaded and has logged in,
>
>When the user enters the command `deactivate blacklist`,
>
>Then the program will prompt for a blacklist name,
>
>Given the user has provided a valid blacklist name,
>
>When the user enters it,
>
>Then the selected blacklist will have its active value switched to false (checkable by using `show blacklist` command)
--------------------
>Scenario: The Businessman wants to deactivate a blacklist that does not exist
>
>Given the user has logged in,
>
>When the user enters the command `deactivate blacklist`,
>
>Then the program will prompt for a blacklist name,
>
>Given the user has provided an invalid blacklist name,
>
>When the user enters it,
>
>Then program will inform the user that it is unable to find the selected blacklist.

### User Story 5:
#### Story:
As a Businessman, I want to be able to create a blacklist so that I can more accurately filter my news.
#### Acceptance Criteria:
>Scenario: The Businessman wants to create a blacklist named: `newBlacklist`.
>
>Given the user has logged in,
>
>When the user enters the command `create blacklist`,
>
>Then the program will prompt for a blacklist name,
>
>Given the user has provided a valid blacklist name,
>
>When the user enters it,
>
>Then the selected new blacklist will be created adn added to the users profile (checkable by using `show blacklist` command)

### User Story 6:
#### Story:
As a Businessman, I want to be able to save my profile so that I can continue my work later.
#### Acceptance Criteria:
>Scenario: The Businessman wants to save his profile.
>
>Given the user has logged in,
>
>When the user enters the command `save profile`,
>
>Then the program will save his profile to be loaded later.

### User Story 7:
#### Story:
As a Businessman, I want to be able to load my profile so that I can continue my work.
#### Acceptance Criteria:
>Scenario: The Businessman wants to load his profile.
>
>Given the user has logged in,
>
>When the user enters the command `load profile`,
>
>Then the program will prompt for a username
>
>Given the user knows the profile they wish to load, 
>
>When the user enters the username,
>
>Then the profile is loaded.
---------
>Scenario: The Businessman wants to load his profile but it does not exist.
>
>Given the user has logged in,
>
>When the user enters the command `load profile`,
>
>Then the program will prompt for a username
>
>Given the desired profile does not exist in storage, 
>
>When the user enters the username,
>
>Then the system informs the user of what went wrong when trying to load his profile.

### User Story 9:
#### Story:
As an User, I want to be able to load the stocklist so that all users get new stocks to work with.
#### Acceptance Criteria:
>Scenario: The User has added new stocks and wants to save them.
>
>Given the User has logged in.
>
>When the User enters the command `load stocks`,
>
>Then the program will load the stock list.

### User Story 8:
#### Story:
As an Admin, I want to be able to save the stocklist so that all users get new stocks to work with.
#### Acceptance Criteria:
>Scenario: The Admin has added new stocks and wants to save them.
>
>Given the Admin has logged in.
>
>When the Admin enters the command `save stocks`,
>
>Then the program will save the stock list

## User Stories M1

### User Story 1:
#### Story:
As a Businessman, I want to be able to add a file of blacklists so that I can have my own custom filters.
#### Acceptance Criteria:
>Scenario: The Businessman wants to import their own custom blacklists.
>
>Given I have started the program and selected either role,
>
>When when I enter the command `add blacklists`,
>
>Then a file selection window will pop up.
>
>Given the user has a valid csv file to upload containing blacklists,
>
>When the user selects the valid csv file,
>
>Then The blacklists within will be imported into the user.
#### Analysis:
* Code for adding a file of blacklists resides in the `stockNews.Blacklist` class.
* Single responsibility: It only imports the blacklists. It references the `stockNews.CSVImporter` class which itself is built with both functions being responsible only for one task. (Importing and parsing)
* Open closed: The `stockNews.CSVImporter` class makes use of the open/closed principle because it is an implementation of my `stockNews.DataImporter`. Thus, the `stockNews.DataImporter` is closed for modification but open to extension.

### User Story 2:
#### Story:
As a Businessman, I want to be able to see my list of available blacklists and their contents so that I may know what filters are active.
#### Acceptance Criteria 2:
>Scenario: The Businessman wants to know what blacklists are loaded into the program.
>
>Given I have started the program, selected either role and have at least one blacklist,
>
>When I enter the command `show blacklists`,
>
>Then program displays a list of available blacklists
>
#### Analysis:
* The implementation of show blacklists resides in the `stockNews.roles.User` class under the method `blacklistsToString`.
* Single responsibility: It does only one thing. It constructs and returns a string of the available blacklists.

### User Story 3:
#### Story 3:
As the Administrator, I want to be able to add stocks (with search terms) that can selected by the user so that users can view a greater variety of stocks.
#### Acceptance Criteria 3:
>Scenario: The Businessman wants to view new stocks.
>
>Given I have started the software and selected admin mode,
>
>When I enter the command `add stocks`,
>
>Then a file selection window will pop up.
>
>Given the user has a valid csv file containing stocks to upload,
>
>When the user selects that valid file,
>
>Then the stocks within will be imported into the software.


#### Analysis:
* The implementation of import stocks is contained in the `stockNews.roles.Admin` class, specifically in the method `addStocks`.
* Open closed: Add stocks makes use of the `stockNews.CSVImporter` in the same way add blacklists does.

### User Story 4:
#### Story:
As a Businessman, I want to be able to see a list of stocks that I can view so I know what stocks are available to me.
#### Acceptance Criteria 4:
>Scenario: Either user is about to select a stock to view but does not know what stocks are available.
>
>Given I have started the software and selected either permission level and added at least one stock,
>
>When I enter the command `show stocks`,
>
>Then the software will list all stock codes that are available.

#### Analysis:
* The implementation of list stocks is contained in the `stockNews.App` class, specifically in the method `showStocks`.
* Single responsibility: It is responsible only for returning a list of stocks correctly formatted.

#### User Story 5:
As a Businessman, I want to be able to see a list of labeled stockNews.news articles related to the chosen stock, labeled according to my blacklists as trustworthy or not so I can make quick investment decisions off of credible data.
#### Acceptance Criteria 5:
>Scenario: Businessman is doing investment research on a given stock.
>
>Given I have started the software and imported at least one stock,
>
>When I type the command `check news`,
>
>Then the software will prompt for a stock code.
>
>Given I know what stock I want to view,
>
>When I enter a stock code that I have imported,
>
>Then the software will show a list of article links from google that have been marked as trustworthy or not according to any imported blacklists. 

#### Analysis:
* The implementation of check news stocks is contained in the `stockNews.App` class, specifically in the method `checkNews`.
* Single responsibility: While the `checkNews` method does many things, it is actually putting together the many other processes to produce an output. The newsApi and newsFilter handle their own parts of the work and nothing else.