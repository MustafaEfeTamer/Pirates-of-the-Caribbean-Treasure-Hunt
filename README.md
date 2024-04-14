the purpose of the game : The character must start from a point and collect all the treasures on the map, but when he collects all the treasures, he must have traveled the shortest path at the end of the program. This is achieved with the shortest path algorithm we have established.


##### When the program is first launched, an interface with a button named "Go to Game Screen" and an image from the movie Pirates of the Caribbean greets us. If we click the button, it will take us to the game screen.
![Ekran görüntüsü 2024-04-01 192744](https://github.com/MustafaEfeTamer/Pirates-of-the-Caribbean-Treasure-Hunt/assets/119308432/e95c585a-f626-4c85-981a-35f9265fbe95)


##### In the game interface, there are three buttons. The first is the "create new map" button, the second is the "start" button, and the third is the "results" button. When the first button is pressed, the map is randomly reestablished, and all obstacles, treasures, and the character's positions are randomly determined. When the second button is pressed, the character starts moving and leaves a green trail behind. The character's goal is to collect all the treasures on the map, and by the end of the game, the character should have taken the shortest path. Once all the treasures on the map are collected, the character stops. The character must avoid both dynamic and static obstacles while moving.
![Ekran görüntüsü 2024-04-01 192945](https://github.com/MustafaEfeTamer/Pirates-of-the-Caribbean-Treasure-Hunt/assets/119308432/7e704909-0c81-4264-ad47-68eaccd2c84d)
![Ekran görüntüsü 2024-04-01 193103](https://github.com/MustafaEfeTamer/Pirates-of-the-Caribbean-Treasure-Hunt/assets/119308432/53259141-3e79-4eb8-b1c9-391efc152080)


##### In the results interface, there are three fields. The first field contains "treasures discovered in order of priority," the second contains "traveled coordinates," and the third contains "total number of steps." In the first field, treasures and their coordinates are displayed in the order they were collected, then arranged by the priority order of the treasures: "gold" at the top, followed by "silver," then "emerald," and lastly "copper" at the bottom. In the second field, the coordinates the character traveled from the beginning to the end of the game are shown step by step. In the last field, the total number of steps taken by the character from the start to the end of the game, or the total distance traveled, is displayed.
![Ekran görüntüsü 2024-04-01 193138](https://github.com/MustafaEfeTamer/Pirates-of-the-Caribbean-Treasure-Hunt/assets/119308432/de96a18d-886d-4a22-95e4-cc62ea5b50d1)
