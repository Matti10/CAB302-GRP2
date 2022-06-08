package maze;

public class imageLocation {
    String path;
    String name;
    Coordinate topLeftLocation; //top left cell of image
    Coordinate bottomRightLocation;

    int size;

    imageLocation(String path, String name, Coordinate topLeftLocation,Coordinate bottomRightLocation, int size)
    {
        this.path = path;
        this.name = name;
        this.topLeftLocation = topLeftLocation;
        this.bottomRightLocation = bottomRightLocation;
        this.size = size;
    }

    imageLocation getImage()
    {
        return this;
    }

    Coordinate[] getImageLocation()
    {
        Coordinate[] arr = new Coordinate[2];
        arr[0] = topLeftLocation;
        arr[1] = bottomRightLocation;
        return arr;
    }
}
