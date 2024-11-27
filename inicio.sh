while true; do
    echo "MENU"
    echo "----"
    echo "1) Crear un control de versión (inicializar .git)"
    echo "2) Configurar usuario: nombre, email y password"
    echo "3) Realizar primer commit"
    echo "4) Moverse entre commits"
    echo "5) Configurar repositorio remoto"
    echo "6) Cambiar rama principal de 'master' a 'main'"
    echo "7) Subir el repositorio local al remoto"
    echo "8) Salir"
    read -p "Seleccione una opción: " opcion

    case $opcion in
        1)
            echo "Creando control de versiones..."
            git init
            echo "Repositorio Git inicializado."
            ;;
        2)
            read -p "Ingrese su nombre: " nombre
            read -p "Ingrese su email: " email
            read -s -p "Ingrese su token/password: " password
            echo
            git config user.name "$nombre"
            git config user.email "$email"
            git config user.password "$password"
            echo "Información del usuario configurada."
            ;;
        3)
            echo "Verificando el estado de los archivos..."
            git status
            echo "Añadiendo archivos al área de preparación..."
            git add .
            read -p "Ingrese un mensaje para el commit: " mensaje
            git commit -m "$mensaje"
            echo "Commit realizado: '$mensaje'."
            ;;
        4)
            echo "Mostrando commits recientes..."
            git log --oneline
            read -p "Ingrese el ID del commit al que desea moverse: " commit_id
            git checkout $commit_id
            echo "Movido al commit $commit_id."
            ;;
        5)
            read -p "Ingrese la URL del repositorio remoto: " url_repo
            git remote add origin "$url_repo"
            echo "Repositorio remoto configurado: $url_repo."
            ;;
        6)
            echo "Renombrando la rama 'master' a 'main'..."
            git branch -m master main
            echo "Cambio completado."
            ;;
        7)
            echo "Subiendo el repositorio local al remoto..."
            git push -u origin main
            echo "Repositorio subido con éxito."
            ;;
        8)
            echo "Saliendo del programa..."
            break
            ;;
        *)
            echo "Opción no válida. Inténtelo de nuevo."
            ;;
    esac
    echo
done
