# TrashCollector

Trash Collector

Gather street to street distanes in km from google-maps Api and store it in CSV files and MySQL Database.

Add distances to CSV files
  - writeDistancesToXLS(apiKeys)
      This method is responsible for reading the csv file from the input folder i.e Toursheet_Friday.xlsx and gather all the street to           street distances from google api for the streets and create new csv file related to every street with street to street distances.
      for-example if Toursheet_Friday.xlsx contains 100 streets it will create 100 new csv files with street address and distance in km in       the output folder based on individual street.
  - input folder for CSV's with streets addresses
    data/tour_sheets
  -output folder for CSV's with streets and distances from google-maps api
    data/gmap_distances/TourSheet_Complete
    
Add distances to local SQL DB    
  - MySQLAccess.writeAddressesToSQLDB2()
      This method is responsible for reading all the csv files created by writeDistancesToXLS() and add all the streets to the SQL DB           table called LOCATIONS and street to street distances to the other table called DISTANCES.
  #The db has two tables i.e locations and distances.
    - LOCATIONS (loc_id int, address varchar, primary key (loc_id))
    - DISTANCES(from_loc_id int, to_loc_id int, distance_in_m double, primary key(from_loc_id, to_oc_id))


Also it creates a .tsp file

#input folder
  -input/can_list/TourSheet.xlsx
  - Input Excel sheet with trash can data
  - list of all trash cans for simulation
  - filename: "TourSheet"

#output folder
  - output/Tour.tsp
  - Output .tsp file for ACO algorithm test run
  
