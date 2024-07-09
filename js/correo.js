const nodemailer = require('nodemailer');

// Configurar el transportador de correo
const transporter = nodemailer.createTransport({
    service: 'gmail', // Puedes usar otro servicio de correo
    auth: {
        user: 'mercadounicachi1@gmail.com', // Tu dirección de correo
        pass: 'Jessicaf10$'   // Tu contraseña
    }
});

// Configurar el correo electrónico
const mailOptions = {
    from: 'mercadounicachi1@gmail.com',
    to: 'jessivaza10@gmail.com',
    subject: 'Restablecimiento de Contraseña',
    text: 'Aquí está tu nueva contraseña: 123456'
};

// Enviar el correo electrónico
transporter.sendMail(mailOptions, function(error, info) {
    if (error) {
        console.log('Error al enviar el correo:', error);
    } else {
        console.log('Correo enviado:', info.response);
    }
});
