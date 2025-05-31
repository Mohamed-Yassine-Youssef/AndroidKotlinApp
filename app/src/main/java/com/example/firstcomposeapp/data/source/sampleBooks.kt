package com.example.firstcomposeapp.data.source
import com.example.firstcomposeapp.R
import com.example.firstcomposeapp.domain.model.Book

object FakeBookDataSource {
 val sampleBooks = listOf(
    Book(
        id = 1,
        title = "Père riche, père pauvre",
        author = "Robert Kiosaki",
        genre = "business",
        description = "un livre qui :\n" +
                "Brise le mythe selon lequel il faut gagner beaucoup d'argent pour devenir riche.\n" +
                "Remet en question cette croyance voulant que votre maison est un actif.\n" +
                "Explique aux parents pourquoi ils ne doivent pas se fier au système d'éducation pour enseigner les rudiments de l'argent à leurs ",
        publishedYear = 1943,
        rating = 4.6f,
        coverResId = R.drawable.richdada,
        amazonLink="https://www.amazon.fr/P%C3%A8re-riche-p%C3%A8re-pauvre-anniversaire/dp/289225955X",
    ),
    Book(
        id = 2,
        title = "Théorie du Laissez-Faire",
        author = "Mel Robbins",
        genre = "Développement Personnel",
        description = " ce livre vous donne l'état d'esprit et les outils pour libérer votre plein potentiel. Commandez votre exemplaire de La théorie du laissez-les faire maintenant et découvrez tout le pouvoir que vous avez vraiment. Tout commence par deux mots simples.",
        publishedYear = 1949,
        rating = 3.9f,
        coverResId = R.drawable.letthem,
        amazonLink="https://www.amazon.fr/Let-Them-Theory-Life-Changing-Millions/dp/B0DFMXHNHG/ref=sr_1_1?crid=3OMYTHXI5NM0Y&dib=eyJ2IjoiMSJ9.Cra5mBi09KTdlYFWoWu_Lnb-rn75y5D6Hg3uKqKQuvXKeRrHuw1YGXnxXoubc6QPdOEuoPCSorQxo_rKxqweBMwUzlySW7HVEeuILTja-XDMK6V9N2FqPeAwmI81XlUIlga32dJvGrEBuP4lTYj03KDVdOzpMa1zlo2zs4-h97IZt8MzoT7AzmTL0Stqbc0uAMCG2pzjU-EdUENEdqX97eBJyRIsOldmSytcSdS1p6MNCKFBJzz9pS5Ul6Fj4G9wjpeJx4zBtbL82oQKkLJoUB5OFpUaEDbRVzscq8lRxK0.OiedS6TtzfrpsCBhLn-8xdNM_Nj9xqFHz8lk1ctn0_Q&dib_tag=se&keywords=the+let+them+theory+mel+robbins&qid=1748687026&sprefix=the+let+%2Caps%2C914&sr=8-1",

        ),
    Book(
        id = 3,
        title = "Sports 40 champions olympiques",
        author = "Jean-Michel BillioudJean-Michel Billioud",
        genre = "Sport",
        description = "Tous les exploits sportifs des 40 plus grands athlètes qui ont marqué l'histoire des Jeux Olympiques. Depuis Spirydon Louis, vainqueur du marathon des premiers Jeux modernes en 1896 à Athènes, à Michael Edgson",
        publishedYear = 1813,
        rating = 4.6f,
        coverResId = R.drawable.sport,
        amazonLink="https://www.amazon.com/-/es/Sports-champions-olympiques-Jean-Michel-Billioud/dp/2075135649",

        ),
    Book(
        id = 4,
        title = "Cahier d'exercices en philosophie",
        author = "Thomas Jesuha",
        genre = "Philosophie",
        description = "Ce cahier de questions et d'exercices est destiné aux lycéens (HLP et philosophie) ainsi qu’aux étudiants de classes préparatoires littéraires et de licence de philosophie. ",
        publishedYear = 1960,
        rating = 4.5f,
        coverResId = R.drawable.philosophie,
        amazonLink="https://www.amazon.fr/Cahier-dexercices-philosophie-connaissances-fondamentales/dp/2759054519",

        ),
    Book(
        id = 5,
        title = "ENCYCLOPEDIE DE LA SCIENCE FICTION",
        author = "Robert Holdstock",
        genre = "Science Fiction",
        description = "L'histoire d'un jeune garçon qui découvre qu'il est un sorcier et entre dans une école de magie.",
        publishedYear = 1997,
        rating = 4.7f,
        coverResId = R.drawable.science,
        amazonLink="https://www.amazon.fr/Encyclop%C3%A9die-science-fiction-Robert-Holdstock/dp/2731800011",

        ),
    Book(
        id = 6,
        title = "Le Secret de la Cabane Interdite",
        author = "Tessa Scott",
        genre = "Enfants",
        description = "L'histoire de Meursault, un homme indifférent qui commet un meurtre apparemment sans motif.",
        publishedYear = 1942,
        rating = 4.5f,
        coverResId = R.drawable.enfants,
        amazonLink="https://www.amazon.fr/Livre-pour-enfants-ans-Interdite/dp/B0F2HLZ1H4/ref=zg_bs_g_15517461031_d_sccl_6/258-9564669-9572937?psc=1",

        )
)}