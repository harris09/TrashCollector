# TrashCollector

Trash Collector

Gather street to street distanes in km from google-maps Api and store it CSV files and MySQL Database.

#output folder for CSV's
data/gmap_distances/TourSheet_Complete

#The db has two tables i.e locations and distances.
- LOCATIONS (loc_id int, address varchar, primary key (loc_id))
- DISTANCES(from_loc_id int, to_loc_id int, distance_in_m double, primary key(from_loc_id, to_oc_id))

Also it creates a .tsp file

#input folder

can_list
Input Excel sheet with trash can data
- list of all trash cans for simulation

filename: "TourSheet"

generates:

#output folder
- Output .tsp file for ACO algorithm test run