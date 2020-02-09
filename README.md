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
stockCode,searchTerm1,searchTerm2
stockCode,searchTerm3,searchTerm4
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

#### User Story 1:
As a Businessman, I want to be able to see my list of available blacklists and their contents so that I may know what filters are active.
#### Acceptance Criteria 1:
>Scenario: The Businessman 
>
>Given I have started the program and added at least one blacklist.
>
>When I enter the command `show blacklists`
>
>Then program displays a list of available blacklists

#### User Story 2:
As a Businessman, I want to be able to add a file of blacklists so that I can have my own custom filters.
#### Acceptance Criteria 2:
>Scenario:
>
>Given
>
>When
>
>Then

#### User Story 3:
As a Businessman, I want to be able to see a list of stocks that I can view so I know what stocks are available to me.
#### Acceptance Criteria 3:
>Scenario:
>
>Given
>
>When
>
>Then


#### User Story 4:
As a Businessman, I want to be able to see a list of labeled news articles related to the chosen stock, labeled according to my blacklists as trustworthy or not so I can make quick investment decisions off of credible data.
#### Acceptance Criteria 4:
>Scenario:
>
>Given
>
>When
>
>Then

#### User Story 5:
As the Administrator, I want to be able to add stocks (with search terms) that can selected by the user so that users can view a greater variety of stocks.
#### Acceptance Criteria 5:
>Scenario:
>
>Given
>
>When
>
>Then