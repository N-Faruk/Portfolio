
ArrayList<Adjustment> data = new ArrayList<Adjustment>();

String[] types = {"Pin", "Smooth", "Rewrap", "Tighten", "Tuck"};

color[] typeColors = {
  color(140,110,170),
  color(230,150,130),
  color(120,150,130),
  color(100,140,180),
  color(220,200,120)
};

// Controls whether the key is visible
boolean showKey = true;

void setup() {
  size(800, 500);
  noLoop();

  // My collected data
  addObservation(1, "Pin", "Home");
  addObservation(1, "Smooth", "Outside");
  addObservation(1, "Pin", "University");
  addObservation(2, "Rewrap", "Home");
  addObservation(2, "Tighten", "Outside");
  addObservation(3, "Smooth", "Home");
  addObservation(4, "Tighten", "Home");
  addObservation(4, "Rewrap", "University");
  addObservation(4, "Tuck", "Outside");
  addObservation(4, "Smooth", "Home");
  addObservation(5, "Tighten", "Home");
  addObservation(5, "Rewrap", "University");
  addObservation(5, "Tuck", "Outside");
  addObservation(5, "Smooth", "Home");
  addObservation(6, "Tighten", "Home");
  addObservation(6, "Rewrap", "University");
  addObservation(6, "Tuck", "Outside");
  addObservation(6, "Smooth", "Home");
  addObservation(7, "Rewrap", "Home");
  addObservation(7, "Tighten", "Outside");
  addObservation(8, "Smooth", "Home");
  addObservation(9, "Tighten", "Home");
  addObservation(9, "Rewrap", "University");
  addObservation(9, "Tuck", "Outside");
  addObservation(9, "Smooth", "Home");
  addObservation(10, "Tighten", "Home");
  addObservation(10, "Rewrap", "University");
  addObservation(10, "Tuck", "Outside");
  addObservation(10, "Smooth", "Home");
  addObservation(11, "Rewrap", "Home");
  addObservation(11, "Tighten", "Outside");
  addObservation(12, "Smooth", "Home");
  addObservation(13, "Tuck", "Home");
}

void draw() {
  background(248, 249, 250);

  float chartX = 100;
  float chartY = height - 100;
  float chartWidth = width - 200;
  float chartHeight = height - 200;

  // Gets the highest day number from my data
  int maxDays = getMaxDays();

  if (maxDays == 0) {
    return;
  }

  float spacing = chartWidth / maxDays;
  float barWidth = spacing * 0.7;

  // Draws the bottom line of the chart
  stroke(200);
  strokeWeight(2);
  line(chartX, chartY, chartX + chartWidth, chartY);

  // Goes through each day and draws its bar
  for (int d = 1; d <= maxDays; d++) {
    float currentX = chartX + (d - 1) * spacing
      + (spacing - barWidth) / 2;

    float currentYOffset = 0;

    // Checks if there are any adjustments for this day
    if (getAdjustmentCount(d) > 0) {
      for (int i = 0; i < data.size(); i++) {
        Adjustment a = data.get(i);

        if (a.day == d) {
          float segmentHeight = chartHeight / 5.5;

          // Gets the colour for the adjustment type
          fill(getColorForType(a.type));
          noStroke();

          // Draws each adjustment as a section of the bar
          rect(currentX,
            chartY - currentYOffset - segmentHeight,
            barWidth, segmentHeight - 3, 4);

          // Adds a symbol to show where the adjustment happened
          drawPlaceMarker(a.place,
            currentX + barWidth / 2,
            chartY - currentYOffset - segmentHeight / 2);

          currentYOffset = currentYOffset + segmentHeight;
        }
      }
    }

    // Adds the day number underneath each bar
    fill(120);
    textAlign(CENTER, TOP);
    textSize(11);
    text("D" + d, currentX + barWidth / 2, chartY + 12);
  }

  // Only draws the key when showKey is true
  if (showKey) {
    drawSimpleKey(chartX, 35, chartWidth);
  }
}


// Checks if the K key has been pressed
void keyPressed() {
  if (key == 'k' || key == 'K') {
    showKey = !showKey;
    redraw();
  }
}


// METHOD 1: Finds the highest day number in my data
// Returns an int and does not need a parameter
int getMaxDays() {
  int maxDay = 0;

  for (int i = 0; i < data.size(); i++) {
    if (data.get(i).day > maxDay) {
      maxDay = data.get(i).day;
    }
  }

  return maxDay;
}


// METHOD 2: Counts the adjustments on a particular day
// Takes a day as a parameter and returns an int
int getAdjustmentCount(int day) {
  int count = 0;

  for (int i = 0; i < data.size(); i++) {
    if (data.get(i).day == day) {
      count = count + 1;
    }
  }

  return count;
}


// Draws a different symbol depending on the location
void drawPlaceMarker(String placeName, float x, float y) {
  fill(255, 230);
  noStroke();
  rectMode(CENTER);

  if (placeName.equals("Home")) {
    ellipse(x, y, 10, 10);
  } else if (placeName.equals("University")) {
    stroke(255, 230);
    strokeWeight(2);
    noFill();
    ellipse(x, y, 10, 10);
  } else if (placeName.equals("Outside")) {
    rect(x, y, 9, 9);
  }

  rectMode(CORNER);
}


// Draws the colour and location keys at the top of the chart
void drawSimpleKey(float x, float y, float w) {
  textSize(12);
  textAlign(LEFT, CENTER);

  // Shows the colours for each adjustment type
  float itemWidth = w / 5;

  for (int i = 0; i < types.length; i++) {
    fill(typeColors[i]);
    noStroke();
    ellipse(x + (i * itemWidth) + 5, y, 12, 12);
    fill(100);
    text(types[i], x + (i * itemWidth) + 22, y);
  }

  // Shows the symbols for each location
  float placeY = y + 25;

  fill(150);
  noStroke();
  ellipse(x + 5, placeY, 10, 10);
  fill(100);
  text("Home", x + 22, placeY);

  stroke(150);
  strokeWeight(2);
  noFill();
  ellipse(x + 150, placeY, 10, 10);
  fill(100);
  text("University", x + 167, placeY);

  fill(150);
  noStroke();
  rect(x + 300, placeY - 4, 9, 9);
  fill(100);
  text("Outside", x + 317, placeY);
}


// Adds a new adjustment to my ArrayList
void addObservation(int day, String type, String place) {
  data.add(new Adjustment(day, type, place));
}


// Finds the colour that matches the adjustment type
color getColorForType(String typeStr) {
  for (int i = 0; i < types.length; i++) {
    if (types[i].equals(typeStr)) {
      return typeColors[i];
    }
  }

  return color(200);
}


// Stores the day, type and place of each adjustment
class Adjustment {
  int day;
  String type;
  String place;

  Adjustment(int d, String t, String p) {
    day = d;
    type = t;
    place = p;
  }
}
