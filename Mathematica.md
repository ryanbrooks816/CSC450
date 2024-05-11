## Used for plotting data in 3D
(*Import the CSV file*) data = Import["a3_p1_f5.csv", "CSV"];

(*Remove the header row*) data = Rest[data];

(*Separate the data into x,y,and z values*) xValues = 
 data[[All, 1]]; yValues = data[[All, 2]]; zValues = data[[All, 3]];

(*Create a 3D plot of the data*) 
pointsPlot = ListPointPlot3D[data, PlotStyle -> PointSize[0.02]];

(*Define the ground function*) groundFunction[x_, y_] := 0;

(*Create a 3D plot of the ground function*) 
groundPlot = 
  Plot3D[groundFunction[x, y], {x, Min[xValues], Max[xValues]}, {y, 
    Min[yValues], Max[yValues]}];

(*Show both plots together*) Show[pointsPlot, groundPlot]


## Used for plotting data and the ground function in 3D, both from CSV files
(*Import the CSV files*)
data = Import["a3_p1_f1.csv", "CSV"];
groundData = Import["a3_p1_f1_ground.csv", "CSV"];

(*Remove the header row*)
data = Rest[data];

(*Separate the data into x,y,and z values*)
xValues = data[[All, 1]];
yValues = data[[All, 2]];
zValues = data[[All, 3]];
(*Separate the ground data into x,y,and z values*)
xGroundValues = groundData[[All, 1]];
yGroundValues = groundData[[All, 2]];
zGroundValues = groundData[[All, 3]];

(*Create a 3D plot of the data*)
pointsPlot = ListPointPlot3D[data, PlotStyle -> PointSize[0.02]];
(*Create a 3D plot of the ground data*)
groundPlot = ListPointPlot3D[groundData, PlotStyle -> PointSize[0.02]];

(*Show both plots together*)
Show[pointsPlot, groundPlot]