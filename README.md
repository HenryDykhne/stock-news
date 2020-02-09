# STOCK NEWS
## Notes:
* The `run` method in the `StockNews` class is intended only for demonstration for m1 and thus breaks single responsibility.
* There is no persistence yet. In order to use features such as checking the news, one must first import a stock csv as an admin and check it in the same running instance.
* The directory called `testFiles` contains files that can be tested with and provide the template for how to build new data.

## Data Structure:
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
2. Run following commands in terminal to build project: 
```
./gradlew build
./gradlew run --console=plain
```

## User Stories

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


#### User Story 5:
As a Businessman, I want to be able to see a list of labeled news articles related to the chosen stock, labeled according to my blacklists as trustworthy or not so I can make quick investment decisions off of credible data.
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

