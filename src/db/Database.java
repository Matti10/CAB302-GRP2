//package db;
//
//public class Database
//{
//    class Table extends Database
//    {
//        static void create_table()
//        {
//
//        }
//
//
//        public String[][] fetchData()
//        {
//            String[][] tableArray = new String[4][databaserowcount];
//            for (int i=0;i<databaserowcount-1;i++) // so the strMaze isn't displayed
//            {
//                for (int j=0;j<5;j++)
//                {
//                    table[j][i] = databaserow[j][i];
//                }
//            }
//            return tableArray;
//        }
//
//        public void SaveMaze(Maze maze)
//        {
//            String name = maze.name;
//            String author = maze.author;
//            String dateCreated = maze.created;
//            String dateEdited = maze.edited;
//
//            String mazeStr;
//            for (int i=0;i<maze.y;i++)
//            {
//                for (int j=0;j<maze.x;j++)
//                {
//                    mazeStr += maze[x,y].str;
//                    if (j=maze.x-1) mazeStr+=";"; // much more efficient if maze is one-dimensional array.
//                    else mazeStr+=",";
//                }
//            }
//
//            //add fields to db
//        }
//
//        public void LoadMaze(String mazeName)
//        {
//            //query mazeName
//            //explode strMaze into cells, rebuild maze
//            new Maze();
//        }
//
//        public int getSize()
//        {
//           return 0;
//        }
//
//        public void deleteMaze()
//        {
//
//        }
//
//        public void close()
//        {
//
//        }
//
//
//
//
//    }
//}
