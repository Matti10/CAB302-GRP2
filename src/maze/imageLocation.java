package maze;

public class imageLocation {
    String path;
    String name;
    coordinate location;

    imageLocation(String path, String name, coordinate location)
    {
        this.path = path;
        this.name = name;
        this.location = location;
    }

    imageLocation getImageLocation()
    {
        return this;
    }
}
