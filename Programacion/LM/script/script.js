// referencias a elementos
const contenedor = document.getElementById('tarjetasContainer');
const inputBuscar = document.getElementById('buscarInput');
const btnBuscar = document.getElementById('buscarBtn');
const temaClaro = document.getElementById('temaClaro');
const temaOscuro = document.getElementById('temaOscuro');
const temaPersonalizado = document.getElementById('temaPersonalizado');
const btnAnadir = document.getElementById('añadirBtn');
const modalColores = document.getElementById('modalColores');
const modalAnadir = document.getElementById('añadir');
const closeColores = document.getElementById('cerrarColores');
const closeAnadir = document.getElementById('cerrarañadir');
const formAnadir = document.getElementById('formAñadir');
const colorHeader = document.getElementById('colorCabecera');
const colorMain = document.getElementById('colorMain');
const colorFooter = document.getElementById('colorPie');
const aplicarColores = document.getElementById('aplicarColores');
const preview = document.getElementById('preview');

// Elementos para las citas
const modalCita = document.getElementById('modalCita');
const cerrarCita = document.querySelector('.cerrar-cita');
const dropdownItems = document.querySelectorAll('.dropdown-content a');
const vehiculoSeleccionado = document.getElementById('vehiculoSeleccionado');
const formCita = document.getElementById('formCita');
const mensajeConfirmacion = document.getElementById('mensajeConfirmacion');
const citaBtn = document.getElementById('citaBtn');
const dropdown = document.querySelector('.dropdown');

let todasLasTarjetas = [];

// cargar el xml
document.addEventListener('DOMContentLoaded', cargarXML);

function cargarXML() {
    fetch('data/galeria.xml')
        .then(res => res.text())
        .then(str => new DOMParser().parseFromString(str, 'text/xml'))
        .then(xmlDoc => {
            const tarjetasXML = xmlDoc.getElementsByTagName('tarjeta');

            for (let t of tarjetasXML) {
                const titulo = t.getElementsByTagName('titulo')[0]?.textContent || 'Sin título';
                const texto = t.getElementsByTagName('texto')[0]?.textContent || '';
                const imagen = t.getElementsByTagName('imagen')[0]?.getAttribute("enlace") 
                    || 'https://via.placeholder.com/300x200';

                todasLasTarjetas.push({ titulo, texto, imagen });
            }

            renderizarTarjetas(todasLasTarjetas);
        })
        .catch(err => {
            console.error('Error cargando XML:', err);
            contenedor.innerHTML = '<p style="text-align:center; color:red;">No se pudieron cargar los datos </p>';
        });
}

function renderizarTarjetas(tarjetas) {
    contenedor.innerHTML = '';

    if (!tarjetas.length) {
        contenedor.innerHTML = '<p class="sin-resultados">No hay tarjetas que coincidan </p>';
        return;
    }

    tarjetas.forEach(t => {
        const card = document.createElement('div');
        card.className = 'tarjeta';

        const img = document.createElement('img');
        img.src = t.imagen;
        img.alt = t.titulo;

        const h3 = document.createElement('h3');
        h3.textContent = t.titulo;

        const p = document.createElement('p');
        p.textContent = t.texto;

        // Al hacer clic en una tarjeta, se abre el formulario de cita con ese vehículo
        card.addEventListener('click', () => {
            const nombreVehiculo = t.titulo.split('|')[0].trim();
            vehiculoSeleccionado.value = nombreVehiculo;
            modalCita.style.display = 'flex';
        });

        card.append(img, h3, p);
        contenedor.appendChild(card);
    });
}

// buscador
btnBuscar.addEventListener('click', () => {
    const texto = inputBuscar.value.trim().toLowerCase();

    const filtradas = texto
        ? todasLasTarjetas.filter(t => t.titulo.toLowerCase().includes(texto))
        : todasLasTarjetas;

    renderizarTarjetas(filtradas);
});

inputBuscar.addEventListener('keyup', e => { 
    if (e.key === 'Enter') btnBuscar.click(); 
});

// temas
temaClaro.addEventListener('click', () => {
    document.body.className = 'tema-claro';
    temaClaro.classList.add('active');
    temaOscuro.classList.remove('active');
    temaPersonalizado.classList.remove('active');
});

temaOscuro.addEventListener('click', () => {
    document.body.className = 'tema-oscuro';
    temaClaro.classList.remove('active');
    temaOscuro.classList.add('active');
    temaPersonalizado.classList.remove('active');
});

temaPersonalizado.addEventListener('click', () => {
    modalColores.style.display = 'flex';
});

closeColores.addEventListener('click', () => modalColores.style.display = 'none');

aplicarColores.addEventListener('click', () => {
    document.body.style.setProperty('--color-header', colorHeader.value);
    document.body.style.setProperty('--color-main', colorMain.value);
    document.body.style.setProperty('--color-footer', colorFooter.value);

    document.body.className = 'tema-personalizado';

    temaPersonalizado.classList.add('active');
    temaClaro.classList.remove('active');
    temaOscuro.classList.remove('active');

    modalColores.style.display = 'none';
});

// añadir tarjeta
btnAnadir.addEventListener('click', () => { 
    modalAnadir.style.display = 'flex'; 
    preview.style.backgroundImage = ''; 
});

closeAnadir.addEventListener('click', () => modalAnadir.style.display = 'none');

document.getElementById('nuevaImagen').addEventListener('change', e => {
    const file = e.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = event => preview.style.backgroundImage = `url('${event.target.result}')`;
    reader.readAsDataURL(file);
});

formAnadir.addEventListener('submit', e => {
    e.preventDefault();

    const titulo = document.getElementById('nuevoTitulo').value.trim();
    const texto = document.getElementById('nuevoTexto').value.trim();
    const file = document.getElementById('nuevaImagen').files[0];

    if (!file) return alert('Debes seleccionar una imagen');

    const reader = new FileReader();
    reader.onload = event => {
        const nuevaTarjeta = { 
            titulo, 
            texto, 
            imagen: event.target.result 
        };

        todasLasTarjetas.push(nuevaTarjeta);
        renderizarTarjetas(todasLasTarjetas);

        formAnadir.reset();
        preview.style.backgroundImage = '';
        modalAnadir.style.display = 'none';
    };

    reader.readAsDataURL(file);
});

// ========== FUNCIONALIDAD DE CITAS ==========

// Mostrar dropdown al pasar el ratón
citaBtn.addEventListener('mouseenter', function() {
    dropdown.classList.add('show');
});

dropdown.addEventListener('mouseleave', function() {
    dropdown.classList.remove('show');
});

// Click en el botón para mostrar/ocultar
citaBtn.addEventListener('click', function(e) {
    e.stopPropagation();
    dropdown.classList.toggle('show');
});

// Cerrar dropdown si se hace clic fuera
document.addEventListener('click', function(e) {
    if (!dropdown.contains(e.target)) {
        dropdown.classList.remove('show');
    }
});

// Manejar selección de vehículo del dropdown
dropdownItems.forEach(item => {
    item.addEventListener('click', function(e) {
        e.preventDefault();
        const vehiculo = this.getAttribute('data-vehiculo');
        vehiculoSeleccionado.value = vehiculo;
        dropdown.classList.remove('show');
        modalCita.style.display = 'flex';
    });
});

// Cerrar modal de cita
if (cerrarCita) {
    cerrarCita.addEventListener('click', function() {
        modalCita.style.display = 'none';
    });
}

// Cerrar modal al hacer clic fuera
window.addEventListener('click', function(e) {
    if (e.target === modalCita) {
        modalCita.style.display = 'none';
    }
});

// Manejar envío del formulario de cita
if (formCita) {
    formCita.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const datosCita = {
            vehiculo: vehiculoSeleccionado.value,
            nombre: document.getElementById('nombre').value,
            email: document.getElementById('email').value,
            telefono: document.getElementById('telefono').value,
            fecha: document.getElementById('fecha').value,
            hora: document.getElementById('hora').value,
            comentarios: document.getElementById('comentarios').value,
            fechaSolicitud: new Date().toLocaleString()
        };
        
        // Guardar en localStorage
        let citasGuardadas = JSON.parse(localStorage.getItem('citasVehiculos') || '[]');
        citasGuardadas.push(datosCita);
        localStorage.setItem('citasVehiculos', JSON.stringify(citasGuardadas));
        
        // Mostrar mensaje de confirmación
        mensajeConfirmacion.textContent = `¡Gracias ${datosCita.nombre}! Hemos recibido tu solicitud de cita para probar el ${datosCita.vehiculo}. Te contactaremos pronto.`;
        mensajeConfirmacion.classList.add('mostrar');
        
        // Limpiar formulario y cerrar modal
        formCita.reset();
        vehiculoSeleccionado.value = '';
        modalCita.style.display = 'none';
        
        // Ocultar mensaje después de 5 segundos
        setTimeout(() => {
            mensajeConfirmacion.classList.remove('mostrar');
        }, 5000);
    });
}