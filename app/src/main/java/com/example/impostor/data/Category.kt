package com.example.impostor.data

data class Category(
    val id: String = "",
    val name: String,
    val words: List<String>,
    val isCustom: Boolean = false
)

object CategoriesData {
    val categories = listOf(
        Category(
            id = "deportes",
            name = "Deportes",
            words = listOf(
                "Fútbol", "Baloncesto", "Tenis", "Natación", "Voleibol",
                "Golf", "Béisbol", "Rugby", "Hockey", "Atletismo",
                "Ciclismo", "Boxeo", "Esquí", "Surf", "Escalada",
                "Karate", "Judo", "Taekwondo", "Esgrima", "Polo",
                "Criquet", "Bádminton", "Squash", "Softbol", "Waterpolo",
                "Halterofilia", "Gimnasia", "Triatlón", "Maratón", "Paracaidismo",
                "Patinaje", "Snowboard", "Skateboarding", "BMX", "Motociclismo",
                "Automovilismo", "Remo", "Piragüismo", "Vela", "Windsurf",
                "Equitación", "Tiro con arco", "Pentatlón", "Decatlón", "Lucha",
                "Balonmano", "Lacrosse", "Hurling", "Padel", "Pickleball"
            )
        ),
        Category(
            id = "futbolistas",
            name = "Futbolistas",
            words = listOf(
                "Messi", "Cristiano Ronaldo", "Neymar", "Mbappé", "Haaland",
                "Benzema", "Lewandowski", "De Bruyne", "Modric", "Salah",
                "Vinicius Jr", "Pedri", "Gavi", "Bellingham", "Kane",
                "Son Heung-min", "Rodri", "Griezmann", "Saka", "Kvaratskhelia",
                "Hojlund", "Álvarez", "Antony", "Rashford", "Foden",
                "Camavinga", "Tchouaméni", "Valverde", "Courtois", "Ter Stegen",
                "Alisson", "Ederson", "Donnarumma", "Oblak", "Neuer",
                "Van Dijk", "Rüdiger", "Militão", "Araujo", "Marquinhos",
                "Koundé", "Cancelo", "Hakimi", "Walker", "Robertson",
                "Busquets", "Casemiro", "Kroos", "Verratti", "Kimmich",

                "Sergio Ramos", "Piqué", "Thiago Silva", "Chiellini", "Bonucci",
                "Jordi Alba", "Carvajal", "Trent Alexander-Arnold", "Reece James", "Theo Hernández",
                "David Alaba", "Laporte", "Upamecano", "Pau Torres", "Giménez",
                "Otamendi", "Tagliafico", "Aspinicueta", "Godín", "Matip",
                "Koulibaly", "Varane", "De Ligt", "Timber", "Dias",

                "Pogba", "Kanté", "Jorginho", "Fernandes", "Eriksen",
                "Odegaard", "Paquetá", "Enzo Fernández", "Gundogan", "Tielemans",
                "Paredes", "Rabiot", "Saúl", "Kessie", "Barella",
                "Tonali", "Locatelli", "Szoboszlai", "McTominay", "Declan Rice",

                "Ronaldo Nazário", "Ronaldinho", "Pelé", "Maradona", "Zidane",
                "Pirlo", "Xavi", "Iniesta", "Lampard", "Gerrard",
                "Rooney", "Henry", "Totti", "Del Piero", "Rivaldo",
                "Kaká", "Eto'o", "Shevchenko", "Roberto Carlos", "Cafu",
                "Figo", "Beckham", "Bergkamp", "Raúl", "Batistuta",

                "Chicharito", "Hirving Lozano", "Ochoa", "Carlos Vela", "Jiménez",
                "Falcao", "James Rodríguez", "Cuadrado", "Luis Suárez", "Edinson Cavani",
                "Alexis Sánchez", "Arturo Vidal", "Paolo Guerrero", "Claudio Bravo", "Christian Cueva",

                "Martínez", "Di María", "Dybala", "Lautaro Martínez", "Papu Gómez"
            )
        ),
        Category(
            id = "objetos",
            name = "Objetos",
            words = listOf(
                "Silla", "Mesa", "Lámpara", "Libro", "Teléfono",
                "Computadora", "Reloj", "Lápiz", "Botella", "Teclado",
                "Espejo", "Cuchara", "Ventilador", "Mochila", "Paraguas",
                "Tijeras", "Gafas", "Almohada", "Manta", "Vaso",
                "Plato", "Tenedor", "Cuchillo", "Olla", "Sartén",
                "Microondas", "Refrigerador", "Televisor", "Radio", "Cámara",
                "Auriculares", "Mouse", "Monitor", "Impresora", "Escáner",
                "Calculadora", "Linterna", "Batería", "Cargador", "Cable",
                "Llave", "Candado", "Martillo", "Destornillador", "Taladro",
                "Cepillo", "Peine", "Secador", "Plancha", "Aspiradora",

                "Papel", "Carpeta", "Cuaderno", "Bolígrafo", "Regla",
                "Goma", "Sacapuntas", "Tiza", "Pizarra", "Agenda",
                "Ventana", "Puerta", "Pared", "Techo", "Piso",
                "Alfombra", "Cortina", "Persiana", "Cojín", "Sofá",
                "Armario", "Cajón", "Estante", "Librero", "Mesa de café",
                "Banco", "Taburete", "Ropero", "Perchero", "Zapatera",
                "Reloj de pared", "Termo", "Tupper", "Jarra", "Copa",
                "Taza", "Cucharón", "Colador", "Tabla de cortar", "Licuadora",
                "Tostadora", "Horno", "Horno eléctrico", "Batidora", "Procesador",
                "Sandwichera", "Freidora", "Calentador", "Ventilador de techo", "Aire acondicionado",
                "Purificador", "Humidificador", "Deshumidificador", "Radiador", "Calefactor",
                "Cepillo de dientes", "Pasta dental", "Hilo dental", "Enjuague bucal", "Toalla",
                "Esponja", "Jabón", "Shampoo", "Acondicionador", "Crema",
                "Perfume", "Desodorante", "Navaja", "Pinza", "Depiladora",

                "Billetera", "Monedero", "Bolso", "Cinturón", "Sombrero",
                "Gorra", "Guantes", "Bufanda", "Chaqueta", "Abrigo",
                "Camisa", "Pantalón", "Zapatos", "Zapatillas", "Calcetines",
                "Reloj inteligente", "Tablet", "Altavoz", "Proyector", "Control remoto",
                "Piloto", "Carpeta de anillas", "Caja", "Bolsa", "Sobre",
                "Cinta adhesiva", "Pegamento", "Tijera de cocina", "Grapadora", "Grapas",
                "Clips", "Post-it", "Brújula", "Mapa", "Globo terráqueo",
                "Telescopio", "Binoculares", "Microscopio", "Termómetro", "Barómetro",
                "Medidor láser", "Nivel", "Escuadra", "Compás", "Metro",
                "Cinta métrica", "Casco", "Guantes de trabajo", "Chaleco reflectante", "Mascarilla",

                "Bicicleta", "Patineta", "Patines", "Casco de bicicleta", "Candado de bicicleta",
                "Bomba de aire", "Luces de bicicleta", "Carro", "Moto", "Casco de moto",
                "Llanta", "Gato hidráulico", "Llave inglesa", "Aceite", "Coolant",
                "Triángulo de seguridad", "Extintor", "Botiquín", "Mapa vial", "GPS",
                "Maleta", "Valija", "Carrito de compras", "Cesta", "Bolsa reutilizable",
                "Regadera", "Manguera", "Rastrillo", "Pala", "Azadón",
                "Tierra abonada", "Maceta", "Planta", "Rosa", "Cactus",
                "Fertilizante", "Guantes de jardinería", "Regla metálica", "Tornillos", "Tuercas",
                "Clavos", "Arandela", "Brocha", "Rodillo", "Bandeja de pintura",
                "Lija", "Espátula", "Prensa", "Sargentos",

                "Alarma", "Detector de humo", "Cámara de seguridad", "Router", "Modem",
                "Disco duro", "USB", "Memoria SD", "Adaptador", "Regleta",
                "Enchufe", "Bombilla", "Interruptor", "Portarretrato", "Marco",
                "Cuadro", "Póster", "Calendario", "Reloj despertador", "Cortapapel",
                "Abrecartas", "Lupa", "Imán", "Tijera de podar", "Termo eléctrico",
                "Jabón líquido", "Portacepillos", "Toallero", "Cesto de ropa"
            )
        ),
        Category(
            id = "cantantes",
            name = "Cantantes",
            words = listOf(
                "Shakira", "Bad Bunny", "Karol G", "J Balvin", "Rosalía",
                "Maluma", "Rauw Alejandro", "Ozuna", "Daddy Yankee", "Anuel AA",
                "Peso Pluma", "Feid", "Manuel Turizo", "Myke Towers", "Bizarrap",
                "Taylor Swift", "The Weeknd", "Drake", "Beyoncé", "Rihanna",
                "Adele", "Ed Sheeran", "Bruno Mars", "Post Malone", "Dua Lipa",
                "Ariana Grande", "Billie Eilish", "Harry Styles", "Justin Bieber", "Selena Gomez",
                "Coldplay", "Imagine Dragons", "Maroon 5", "OneRepublic", "Twenty One Pilots",
                "BTS", "Blackpink", "Twice", "Stray Kids", "NewJeans",
                "Bad Gyal", "Nathy Peluso", "C. Tangana", "Quevedo", "Aitana",
                "Camilo", "Sebastián Yatra", "Nicki Nicole", "Mora", "Ryan Castro",

                "Luis Fonsi", "Enrique Iglesias", "Ricky Martin", "Chayanne", "Marc Anthony",
                "Romeo Santos", "Prince Royce", "Wisin", "Yandel", "Nicky Jam",
                "Becky G", "Jennifer Lopez", "Camila Cabello", "Alvaro Soler", "Pablo Alborán",
                "David Bisbal", "Melendi", "Lola Índigo", "Morat", "Reik",
                "CNCO", "Luis Miguel", "Cristian Castro", "Juanes", "Carlos Vives",
                "Gloria Trevi", "Thalía", "Paulina Rubio", "Ha*Ash", "Jesse & Joy",
                "Sin Bandera", "Ricardo Arjona", "Ricardo Montaner", "Evaluna", "Lele Pons",
                "TINI", "Danna Paola", "Belinda", "Rebelde", "Christian Nodal",
                "Grupo Firme", "Carín León", "Junior H", "Natanael Cano", "Fuerza Regida",
                "Eladio Carrión", "Sech", "Dalex", "Lenny Tavárez", "Arcángel",

                "Eminem", "Kanye West", "Kendrick Lamar", "Travis Scott", "Lil Nas X",
                "Doja Cat", "SZA", "Halsey", "Charlie Puth", "Shawn Mendes",
                "Katy Perry", "Lady Gaga", "Madonna", "Mariah Carey", "Jennifer Hudson",
                "Sam Smith", "Lizzo", "Florence + The Machine", "Sia", "Zayn",
                "Liam Payne", "Niall Horan", "The Kid Laroi", "Machine Gun Kelly", "Tory Lanez",
                "J. Cole", "Future", "Nicki Minaj", "Cardi B", "Latto",
                "Ellie Goulding", "Jessie J", "Alicia Keys", "Christina Aguilera", "Usher",
                "Jason Derulo", "Ne-Yo", "Chris Brown", "The Script", "Kings of Leon",
                "Arctic Monkeys", "Paramore", "Linkin Park", "Green Day", "Fall Out Boy",
                "Jonas Brothers", "Miley Cyrus", "Demi Lovato", "Avril Lavigne"
            )
        ),
        Category(
            id = "videojuegos",
            name = "Videojuegos",
            words = listOf(
                "Minecraft", "Fortnite", "GTA", "FIFA", "Call of Duty",
                "League of Legends", "Valorant", "Apex Legends", "Overwatch", "Roblox",
                "Among Us", "Fall Guys", "Rocket League", "Counter-Strike", "PUBG",
                "Genshin Impact", "Honkai Star Rail", "Mobile Legends", "Free Fire", "Clash Royale",
                "Clash of Clans", "Brawl Stars", "Pokemon", "Mario Kart", "Super Mario",
                "Zelda", "Animal Crossing", "Splatoon", "Smash Bros", "Metroid",
                "God of War", "The Last of Us", "Uncharted", "Spider-Man", "Horizon",
                "Elden Ring", "Dark Souls", "Bloodborne", "Sekiro", "Monster Hunter",
                "Resident Evil", "Silent Hill", "Final Fantasy", "Dragon Quest", "Persona",
                "Cyberpunk", "Red Dead Redemption", "Assassin's Creed", "Far Cry", "Watch Dogs",

                "Battlefield", "Halo", "Destiny", "Warframe", "Titanfall",
                "Doom", "Quake", "Wolfenstein", "Rage", "Borderlands",
                "Diablo", "Starcraft", "Warcraft", "World of Warcraft", "Hearthstone",
                "Overcooked", "Cuphead", "Hollow Knight", "Celeste", "Dead Cells",
                "Terraria", "Stardew Valley", "Don't Starve", "Subnautica", "Ark",
                "Rust", "DayZ", "Escape from Tarkov", "Path of Exile", "Baldur's Gate",
                "Pillars of Eternity", "Divinity Original Sin", "The Witcher", "The Elder Scrolls", "Skyrim",
                "Morrowind", "Oblivion", "Fallout", "Mass Effect", "Dragon Age",
                "Kingdom Hearts", "Tales of Arise", "Ni No Kuni", "Xenoblade Chronicles", "Fire Emblem",

                "Mortal Kombat", "Street Fighter", "Tekken", "Soul Calibur", "Injustice",
                "Naruto Storm", "Jump Force", "Dragon Ball FighterZ", "Guilty Gear", "BlazBlue",
                "Forza Horizon", "Gran Turismo", "Need for Speed", "The Crew", "Trackmania",
                "NBA 2K", "Madden NFL", "NHL", "PGA Tour", "UFC",
                "Just Dance", "Beat Saber", "Guitar Hero", "Rock Band", "Dance Dance Revolution",

                "Left 4 Dead", "Dying Light", "Dead Island", "Back 4 Blood", "State of Decay",
                "Rainbow Six Siege", "The Division", "Arma 3", "Insurgency", "Squad",
                "Payday", "Hitman", "Mafia", "L.A. Noire", "Sleeping Dogs",

                "Crash Bandicoot", "Spyro", "Ratchet & Clank", "Jak and Daxter", "Sly Cooper",
                "Sonic", "Kirby", "Donkey Kong", "Yoshi", "Luigi's Mansion",
                "Paper Mario", "Pikmin", "Fireboy and Watergirl", "LittleBigPlanet", "Sackboy",

                "Portal", "Half-Life", "BioShock", "Prey", "Dishonored",
                "Control", "Alan Wake", "Quantum Break", "Outlast", "Amnesia",
                "Inside", "Limbo", "Resident Evil Village", "The Evil Within", "The Medium",

                "Cities Skylines", "SimCity", "The Sims", "Planet Zoo", "Planet Coaster",
                "RollerCoaster Tycoon", "Civilization", "Age of Empires", "Humankind", "Crusader Kings",
                "Europa Universalis", "Hearts of Iron", "Total War", "Stronghold", "Banished",

                "Firewatch", "Life is Strange", "Detroit Become Human", "Heavy Rain", "Until Dawn",
                "Story of Seasons", "Harvest Moon", "Spiritfarer", "Ori", "Journey",

                "Splinter Cell", "Metal Gear Solid", "Shadow of the Colossus", "ICO", "The Last Guardian",
                "Control Ultimate Edition", "Manhunt", "Prototype", "Infamous", "Quantum Error",

                "League of Legends Wild Rift", "PUBG Mobile", "Call of Duty Mobile", "Fortnite Mobile", "Asphalt 9",
                "Gang Beasts", "Human Fall Flat", "Among Trees", "Grounded", "V Rising"
            )
        ),
        Category(
            id = "series_peliculas",
            name = "Series y Películas",
            words = listOf(
                "Breaking Bad", "Game of Thrones", "Stranger Things", "The Walking Dead", "Friends",
                "The Office", "How I Met Your Mother", "Big Bang Theory", "Grey's Anatomy", "House",
                "Lost", "Prison Break", "Peaky Blinders", "Vikings", "The Crown",
                "Narcos", "Money Heist", "Dark", "Black Mirror", "The Witcher",
                "Wednesday", "Cobra Kai", "The Mandalorian", "WandaVision", "Loki",
                "Avatar", "Titanic", "Avengers", "Star Wars", "Harry Potter",
                "Lord of the Rings", "Matrix", "Inception", "Interstellar", "The Dark Knight",
                "Pulp Fiction", "Forrest Gump", "The Shawshank Redemption", "Gladiator", "Braveheart",
                "Spider-Man", "Iron Man", "Batman", "Superman", "Wonder Woman",
                "Jurassic Park", "Fast and Furious", "Mission Impossible", "James Bond", "John Wick",

                "The Godfather", "The Godfather 2", "The Godfather 3", "Scarface", "Goodfellas",
                "Fight Club", "The Social Network", "The Wolf of Wall Street", "Django Unchained", "The Hateful Eight",
                "Kill Bill", "Reservoir Dogs", "Once Upon a Time in Hollywood", "La La Land", "Whiplash",
                "Parasite", "Oldboy", "Snowpiercer", "Train to Busan", "Memories of Murder",
                "Shrek", "Shrek 2", "Kung Fu Panda", "Madagascar", "How to Train Your Dragon",
                "Toy Story", "Toy Story 2", "Monsters Inc", "Finding Nemo", "Inside Out",
                "Frozen", "Moana", "Zootopia", "Encanto", "Coco",
                "The Incredibles", "Ratatouille", "Wall-E", "Up", "Soul",

                "Joker", "Logan", "Deadpool", "Black Panther", "Doctor Strange",
                "Guardians of the Galaxy", "Thor Ragnarok", "Captain America", "Hulk", "Ant-Man",
                "Transformers", "Terminator", "RoboCop", "Predator", "Alien",
                "Blade Runner", "Blade Runner 2049", "Dune", "Arrival", "Gravity",
                "E.T.", "Back to the Future", "The Goonies", "Ghostbusters", "Men in Black",
                "Indiana Jones", "Pirates of the Caribbean", "The Hunger Games", "Divergent", "Maze Runner",

                "Rocky", "Creed", "Million Dollar Baby", "Raging Bull", "The Fighter",
                "The Pianist", "Schindler's List", "Saving Private Ryan", "1917", "Hacksaw Ridge",
                "Top Gun", "Top Gun Maverick", "The Great Gatsby", "The Revenant", "Birdman",
                "Her", "The Truman Show", "The Matrix Reloaded", "The Matrix Revolutions", "Tenet",

                "The Prestige", "Memento", "No Country for Old Men", "There Will Be Blood", "The Big Lebowski",
                "Cast Away", "The Curious Case of Benjamin Button", "American Beauty", "A Beautiful Mind", "Gone Girl",
                "Mad Max Fury Road", "The Kingsman", "The Equalizer", "Taken", "The Bourne Identity",
                "Jumanji", "Night at the Museum", "The Chronicles of Narnia", "The Golden Compass", "Pan's Labyrinth"
            )
        ),
        Category(
            id = "clubes",
            name = "Clubes",
            words = listOf(
                "Real Madrid", "Barcelona", "Manchester United", "Liverpool", "Bayern Munich",
                "Juventus", "AC Milan", "Inter Milan", "Arsenal", "Chelsea",
                "Manchester City", "Paris Saint-Germain", "Borussia Dortmund", "Atletico Madrid", "Ajax",
                "Benfica", "Porto", "Sporting", "Roma", "Napoli",
                "Sevilla", "Valencia", "Athletic Bilbao", "Real Sociedad", "Villarreal",
                "Tottenham", "Leicester", "Everton", "Newcastle", "West Ham",
                "Boca Juniors", "River Plate", "Flamengo", "Palmeiras", "Corinthians",
                "Santos", "São Paulo", "América", "Chivas", "Cruz Azul",
                "Racing", "Independiente", "San Lorenzo", "Estudiantes", "Nacional",
                "Peñarol", "Colo-Colo", "Universidad de Chile", "Cerro Porteño", "Olimpia",

                "Galatasaray", "Fenerbahçe", "Besiktas", "Trabzonspor", "Red Bull Salzburg",
                "RB Leipzig", "Bayer Leverkusen", "Schalke 04", "Werder Bremen", "Hamburgo",
                "PSV Eindhoven", "Feyenoord", "Anderlecht", "Club Brugge", "Standard Lieja",
                "Celtic", "Rangers", "Shakhtar Donetsk", "Dynamo Kyiv", "Spartak Moscow",
                "Zenit", "CSKA Moscow", "Monaco", "Lyon", "Marseille",
                "Lille", "Nice", "Galway United", "Basel", "Young Boys",
                "LA Galaxy", "Inter Miami", "New York City FC", "Toronto FC", "Seattle Sounders",
                "Tigres", "Monterrey", "Atlético Nacional", "Millonarios", "Santa Fe",
                "Al Hilal", "Al Nassr", "Al Ahly", "Zamalek", "Kaizer Chiefs"
            )
        ),
        Category(
            id = "Youtubers",
            name = "Youtubers",
            words = listOf(
                "PewDiePie", "MrBeast", "Markiplier", "Jacksepticeye", "Ninja",
                "Pokimane", "Ibai", "AuronPlay", "Rubius", "TheGrefg",
                "ElMariana", "JuanSGuarnizo", "Gerardito", "xQc", "Kai Cenat",
                "Dream", "GeorgeNotFound", "Sapnap", "Technoblade", "TommyInnit",
                "Willyrex", "Vegetta777", "Staxx", "Luzu", "Alexby",
                "Fernanfloo", "Ami Rodriguez", "German Garmendia", "Dross", "Alejo Igoa",
                "Coscu", "Momo", "Sprint", "Coscu Army", "Reven",
                "IlloJuan", "Karmaland", "ByViruzz", "DjMaRiio", "Ampeter",
                "LosPolinesios", "Yolo Aventuras", "Antrax", "JuegaGerman", "Mis Pastelitos",
                "MrBeast Español", "Zarcort", "Dalas Review", "Zorman", "Lyna",
                "Mikecrack", "ElTrollino", "TimbaVK", "Invictor", "Spreen",
                "ElXokas", "Kidi", "Manucraft", "NavajaNegra", "Knekro",
                "Quackity", "FedeVigevani", "Coreano Loco", "AlphaSniper", "Lolito",
                "ElRubiusOMG", "Patty Dragona", "Mangel", "GeorgeNotFound Español", "ElDed",
                "Chimichurri", "BarcaGamer", "Twitch Rivals", "AriGameplays", "La Rivers",
                "ElSpreen", "Carola", "Paracetamor", "Westcol", "RiversGG",
                "Roier", "Quackity Studios", "RicharBetaCode", "Edd00", "Luigikid",
                "Miniminter", "KSI", "Logan Paul", "Jake Paul", "Sidemen",
                "Loretta", "Maryland", "El Ceerre", "SpreenDMC", "ZilverK",
                "MrStiven Tc", "Nexxuz", "Folagor", "Piper Rockelle", "Ben Azelart",
                "Brianda Deyanara", "Kimberly Loaiza", "JD Pantoja", "Tiko", "SSSniperWolf",
                "VanossGaming", "H2ODelirious", "Lachlan", "Fresh", "Lannan Eacott (LazarBeam)",
                "Typical Gamer", "SypherPK", "TimTheTatman", "DrDisRespect", "Shroud",
                "Summit1G", "Asmongold", "HasanAbi", "MoistCr1TiKaL", "Ludwig",
                "Fuslie", "Valkyrae", "Sykkuno", "Corpse Husband", "Rachel Hofstetter",
                "GothamChess", "BotezLive", "Hikaru Nakamura", "GMNaroditsky", "Eric Rosen",
                "MrKeroro10", "GustavoSG", "Mikasaurio", "Maau", "DimeNacho",
                "iTownGameplay", "RaptorGamer", "Lalo", "Plech", "CrackMan",
                "ElCoci", "Komanche", "Grefg Shorts", "Cheeto", "Kori",
                "UnRubius", "Mortis", "Vicens", "TortillaLand", "Axozer",
                "MissaSinfonia", "GravyCatMan", "ItsFunneh", "Aphmau", "PrestonPlayz",
                "BajanCanadian", "CaptainSparklez", "Callux", "Nadeshot", "CourageJD"
            )
        ),
        Category(
            id = "Famosos",
            name = "Famosos",
            words = listOf(
                "Leonardo DiCaprio", "Brad Pitt", "Johnny Depp", "Tom Cruise", "Robert Downey Jr",
                "Dwayne Johnson", "Will Smith", "Chris Hemsworth", "Chris Evans", "Ryan Reynolds",
                "Keanu Reeves", "Hugh Jackman", "Matt Damon", "Ben Affleck", "Samuel L. Jackson",
                "Morgan Freeman", "Al Pacino", "Robert De Niro", "Christian Bale", "Joaquin Phoenix",
                "Adam Driver", "Oscar Isaac", "Mark Ruffalo", "Jason Momoa", "Henry Cavill",
                "Tom Hanks", "Jake Gyllenhaal", "Jared Leto", "Colin Farrell", "Ewan McGregor",
                "Daniel Radcliffe", "Rupert Grint", "Emma Watson", "Michael Fassbender", "James McAvoy",
                "Benedict Cumberbatch", "Martin Freeman", "Andrew Garfield", "Tobey Maguire", "Tom Holland",
                "Chadwick Boseman", "Paul Rudd", "Jeremy Renner", "Don Cheadle", "Josh Brolin",
                "Idris Elba", "Jamie Foxx", "Chris Pratt", "Zac Efron", "Shia LaBeouf",
                "Jude Law", "Orlando Bloom", "Viggo Mortensen", "Sean Bean", "Ian McKellen",
                "Patrick Stewart", "Daniel Craig", "Pierce Brosnan", "Rami Malek", "Timothée Chalamet",
                "Robert Pattinson", "Kristen Stewart", "Taylor Lautner", "Michael B. Jordan", "Forest Whitaker",
                "John Boyega", "Oscar Isaac", "Mahershala Ali", "Kit Harington", "Peter Dinklage",
                "Nicolas Cage", "Bruce Willis", "Sylvester Stallone", "Arnold Schwarzenegger", "Mel Gibson",
                "Antonio Banderas", "Gael García Bernal", "Diego Luna", "Javier Bardem", "Penélope Cruz",
                "Salma Hayek", "Harrison Ford", "Jeff Bridges", "Kevin Costner", "Mark Wahlberg",
                "Ryan Gosling", "Eddie Redmayne", "Colin Firth", "Hugh Grant", "Bill Murray",
                "Jim Carrey", "Robin Williams", "Jack Nicholson", "Heath Ledger", "Brendan Fraser",
                "Ke Huy Quan", "John Travolta", "Denzel Washington", "Bruce Lee", "Jackie Chan"
            )
        )
    )
}
