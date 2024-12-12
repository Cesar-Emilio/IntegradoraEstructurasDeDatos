package mx.edu.utez.videojuegorpg.model;

import mx.edu.utez.videojuegorpg.dataStructures.ArrayList;
import mx.edu.utez.videojuegorpg.enums.Rol;
import mx.edu.utez.videojuegorpg.enums.RolEnemigo;

import java.util.HashMap;
import java.util.Map;

public class EnemigosConfig {

    private static final Map<Integer, ArrayList<Enemigo>> ENEMIGOS_POR_NIVEL = new HashMap<>();

    static {

        ArrayList<Habilidad> habilidadesBasicasEsqueleto = ArrayList.of(
                RolEnemigo.GUERRERO_NORMAL.getHabilidades()
        );

        ArrayList<Habilidad> habilidadesAvanzadasEsqueleto = ArrayList.of(
                RolEnemigo.GUERRERO_ELITE.getHabilidades()
        );

        ArrayList<Habilidad> habilidadesJefeEqueleto = ArrayList.of(
                RolEnemigo.GUERRERO_JEFE.getHabilidades()
        );

        ArrayList<Habilidad> habilidadesBasicasMagoOscuro = ArrayList.of(
                RolEnemigo.MAGO_NORMAL.getHabilidades()
        );

        ArrayList<Habilidad> habilidadesAvanzadasMagoOscuro = ArrayList.of(
                RolEnemigo.MAGO_ELITE.getHabilidades()
        );

        ArrayList<Habilidad> habilidadesJefeMagoOscuro = ArrayList.of(
                RolEnemigo.MAGO_JEFE.getHabilidades()
        );

        ENEMIGOS_POR_NIVEL.put(1, ArrayList.of(
                new Enemigo("Esqueleto", RolEnemigo.GUERRERO_NORMAL, 10, 50, 100),
                new Enemigo("Esqueleto", RolEnemigo.GUERRERO_NORMAL, 10, 50, 100)
        ));

        ENEMIGOS_POR_NIVEL.put(2, ArrayList.of(
                new Enemigo("Esqueleto", RolEnemigo.GUERRERO_NORMAL, 15, 55, 100, habilidadesBasicasEsqueleto),
                new Enemigo("Esqueleto", RolEnemigo.GUERRERO_NORMAL, 15, 55, 100, habilidadesBasicasEsqueleto),
                new Enemigo("Mago oscuro", RolEnemigo.MAGO_NORMAL, 10, 70, 70, habilidadesBasicasMagoOscuro)
        ));

        ENEMIGOS_POR_NIVEL.put(3, ArrayList.of(
                new Enemigo("Esqueleto", RolEnemigo.GUERRERO_NORMAL, 15, 55, 100, habilidadesBasicasEsqueleto),
                new Enemigo("Esqueleto", RolEnemigo.GUERRERO_NORMAL, 15, 55, 100, habilidadesBasicasEsqueleto),
                new Enemigo("Mago oscuro", RolEnemigo.MAGO_NORMAL, 10, 70, 70, habilidadesBasicasMagoOscuro),
                new Enemigo("Mago oscuro", RolEnemigo.MAGO_NORMAL, 10, 70, 70, habilidadesBasicasMagoOscuro)
        ));

        ENEMIGOS_POR_NIVEL.put(4, ArrayList.of(
                new Enemigo("Esqueleto", RolEnemigo.GUERRERO_NORMAL, 15, 55, 100, habilidadesBasicasEsqueleto),
                new Enemigo("Esqueleto", RolEnemigo.GUERRERO_NORMAL, 15, 55, 100, habilidadesBasicasEsqueleto),
                new Enemigo("Mago oscuro", RolEnemigo.MAGO_NORMAL, 10, 70, 70, habilidadesBasicasMagoOscuro),
                new Enemigo("Mago oscuro", RolEnemigo.MAGO_NORMAL, 10, 70, 70, habilidadesBasicasMagoOscuro)
        ));

        ENEMIGOS_POR_NIVEL.put(5, ArrayList.of(
                new Enemigo("Esqueleto", RolEnemigo.GUERRERO_NORMAL, 15, 55, 100, habilidadesBasicasEsqueleto),
                new Enemigo("Esqueleto", RolEnemigo.GUERRERO_NORMAL, 15, 55, 100, habilidadesBasicasEsqueleto),
                new Enemigo("Esqueleto Jefe", RolEnemigo.GUERRERO_ELITE, 20, 60, 150, habilidadesAvanzadasEsqueleto),
                new Enemigo("Mago oscuro", RolEnemigo.MAGO_NORMAL, 10, 70, 70, habilidadesBasicasMagoOscuro),
                new Enemigo("Mago oscuro", RolEnemigo.MAGO_NORMAL, 10, 70, 70, habilidadesBasicasMagoOscuro)
        ));

        ENEMIGOS_POR_NIVEL.put(6, ArrayList.of(
                new Enemigo("Mago oscuro", RolEnemigo.MAGO_NORMAL, 10, 70, 70, habilidadesBasicasMagoOscuro),
                new Enemigo("Esqueleto de élite", RolEnemigo.GUERRERO_ELITE, 15, 55, 100, habilidadesAvanzadasEsqueleto),
                new Enemigo("Esqueleto de élite", RolEnemigo.GUERRERO_ELITE, 20, 60, 150, habilidadesAvanzadasEsqueleto),
                new Enemigo("Mago oscuro", RolEnemigo.MAGO_NORMAL, 10, 70, 70, habilidadesBasicasMagoOscuro)
        ));

        ENEMIGOS_POR_NIVEL.put(7, ArrayList.of(
                new Enemigo("Esqueleto de élite", RolEnemigo.GUERRERO_ELITE, 20, 60, 150, habilidadesAvanzadasEsqueleto),
                new Enemigo("Esqueleto de élite", RolEnemigo.GUERRERO_ELITE, 20, 60, 150, habilidadesAvanzadasEsqueleto)
        ));

        ENEMIGOS_POR_NIVEL.put(8, ArrayList.of(
                new Enemigo("Mago oscuro de élite", RolEnemigo.MAGO_ELITE, 10, 70, 70, habilidadesBasicasMagoOscuro),
                new Enemigo("Mago oscuro de élite", RolEnemigo.MAGO_ELITE, 10, 70, 70, habilidadesBasicasMagoOscuro),
                new Enemigo("Esqueleto de élite", RolEnemigo.GUERRERO_ELITE, 20, 60, 150, habilidadesAvanzadasEsqueleto),
                new Enemigo("Esqueleto de élite", RolEnemigo.GUERRERO_ELITE, 20, 60, 150, habilidadesAvanzadasEsqueleto)
        ));

        ENEMIGOS_POR_NIVEL.put(9, ArrayList.of(
                new Enemigo("Esqueleto de élite", RolEnemigo.GUERRERO_ELITE, 20, 60, 150, habilidadesAvanzadasEsqueleto),
                new Enemigo("Mago oscuro de élite", RolEnemigo.MAGO_ELITE, 10, 70, 70, habilidadesBasicasMagoOscuro),
                new Enemigo("Mago oscuro de élite", RolEnemigo.MAGO_ELITE, 10, 70, 70, habilidadesBasicasMagoOscuro),
                new Enemigo("Mago oscuro de élite", RolEnemigo.MAGO_ELITE, 10, 70, 70, habilidadesBasicasMagoOscuro),
                new Enemigo("Esqueleto de élite", RolEnemigo.GUERRERO_ELITE, 20, 60, 150, habilidadesAvanzadasEsqueleto)
        ));

        ENEMIGOS_POR_NIVEL.put(10, ArrayList.of(
                new Enemigo("Esqueleto de élite", RolEnemigo.MAGO_JEFE, 20, 60, 150, habilidadesAvanzadasEsqueleto),
                new Enemigo("Mago oscuro de élite", RolEnemigo.MAGO_JEFE, 10, 70, 70, habilidadesBasicasMagoOscuro),
                new Enemigo("Mago oscuro de élite", RolEnemigo.MAGO_JEFE, 10, 70, 70, habilidadesBasicasMagoOscuro),
                new Enemigo("Mago oscuro de élite", RolEnemigo.MAGO_JEFE, 10, 70, 70, habilidadesBasicasMagoOscuro),
                new Enemigo("Esqueleto de élite", RolEnemigo.MAGO_JEFE, 20, 60, 150, habilidadesAvanzadasEsqueleto)
        ));
    }

    public static ArrayList<Enemigo> obtenerEnemigos(int nivel) {
        return ENEMIGOS_POR_NIVEL.getOrDefault(nivel, new ArrayList<>());
    }
}