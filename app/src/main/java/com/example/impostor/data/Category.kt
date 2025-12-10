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
                "Busquets", "Casemiro", "Kroos", "Verratti", "Kimmich"
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
                "Cepillo", "Peine", "Secador", "Plancha", "Aspiradora"
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
                "Camilo", "Sebastián Yatra", "Nicki Nicole", "Mora", "Ryan Castro"
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
                "Cyberpunk", "Red Dead Redemption", "Assassin's Creed", "Far Cry", "Watch Dogs"
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
                "Jurassic Park", "Fast and Furious", "Mission Impossible", "James Bond", "John Wick"
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
                "Peñarol", "Colo-Colo", "Universidad de Chile", "Cerro Porteño", "Olimpia"
            )
        )
    )
}
