
/*
  THE LITTLE ADJUSTMENTS - Dear Data 100
  Each mark represents one real hijab adjustment.
  Colour = adjustment type; thickness = daily frequency.
*/

ArrayList<Adjustment> data = new ArrayList<Adjustment>();
color[] colours = {
  #B58BC1, #E9A18A, #819C8C, #83A8C8, #E8D18A
};

void setup() {
  size(1100, 850);
  smooth();


  addObservation(1, "Pin", "Home");
  addObservation(1, "Smooth", "Outside");
  addObservation(1, "Pin", "University");
  addObservation(2, "Rewrap", "Home");
  addObservation(2, "Tighten", "Outside");
  addObservation(3, "Smooth", "University");
}

void draw() {
  background(#F8F1E8);
  drawTitle();
  drawSpiral();
  drawLegend();
}

// Add one observation to the dataset
void addObservation(int day, String type, String place) {
  data.add(new Adjustment(day, type, place));
}

void drawTitle() {
  fill(#39343A);
  textSize(30);
  text("The Little Adjustments", 35, 45);
  textSize(14);
  text(data.size() + " observations collected", 38, 72);
}

void drawSpiral() {
  if (data.size() == 0) return;

  float cx = 425, cy = 430;
  float turns = 4.1, maxR = 330;

  for (int i = 0; i < data.size(); i++) {
    float t = map(i, 0, 99, 0, TWO_PI * turns);
    float r = map(i, 0, 99, 12, maxR);
    float x = cx + r * cos(t);
    float y = cy + r * sin(t);

    int frequency = countDay(data.get(i).day);
    float thickness = min(22, 3 + frequency * 2.5);

    stroke(colours[data.get(i).typeIndex()]);
    strokeWeight(thickness);
    noFill();

    if (i < data.size() - 1) {
      float nextT = map(i + 1, 0, 99, 0, TWO_PI * turns);
      float nextR = map(i + 1, 0, 99, 12, maxR);
      line(x, y, cx + nextR * cos(nextT),
           cy + nextR * sin(nextT));
    }

    noStroke();
    fill(colours[data.get(i).typeIndex()]);
    ellipse(x, y, 7, 7);
  }
  strokeWeight(1);
}

int countDay(int day) {
  int total = 0;
  for (Adjustment a : data) {
    if (a.day == day) total++;
  }
  return total;
}

void drawLegend() {
  String[] labels = {
    "Pin", "Smooth", "Rewrap", "Tighten", "Loosen"
  };

  fill(#39343A);
  textSize(17);
  text("Adjustment type", 830, 125);

  for (int i = 0; i < labels.length; i++) {
    fill(colours[i]);
    noStroke();
    ellipse(845, 165 + i * 43, 19, 19);
    fill(#39343A);
    textSize(14);
    text(labels[i], 865, 170 + i * 43);
  }

  text("Observations: " + data.size(), 830, 410);
  text("Thicker = more adjustments", 830, 440);
  text("on that day.", 830, 460);
  text("Goal: 100 observations", 830, 500);
}

class Adjustment {
  int day;
  String type, place;

  Adjustment(int d, String t, String p) {
    day = d;
    type = t;
    place = p;
  }

  int typeIndex() {
    if (type.equals("Pin")) return 0;
    if (type.equals("Smooth")) return 1;
    if (type.equals("Rewrap")) return 2;
    if (type.equals("Tighten")) return 3;
    return 4;
  }
}

