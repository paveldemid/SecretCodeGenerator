package com.pavdem.secretgenerator;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pavdem.secretgenerator.databinding.ActivityMainBinding;
import com.pavdem.secretgenerator.generator.GeneratorCode;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; // переменная для View Binding
    public GeneratorCode generatorCode = new GeneratorCode();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Инициализация биндинга
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Слушатель кнопки "Генерация"
        binding.generateButton.setOnClickListener(v -> {

            // Получаем текст из поля
            String lengthText = binding.passwordLengthInput.getText().toString().trim();
            Integer length = null;

            // Преобразуем в число
            if (!lengthText.isEmpty()) {
                try {
                    length = Integer.parseInt(lengthText);
                } catch (NumberFormatException e) {
                    length = null;
                }
            }

            // Проверка длины пароля
            if (length == null || length < 4 || length > 32) {
                binding.passwordOutput.setText("Введите длину от 4 до 32");
                return;
            }

            // Получаем состояния чекбоксов
            boolean includeSpecialSymbols = binding.useSpecialSymbols.isChecked();
            boolean includeUseLowercase = binding.useLowercase.isChecked();
            boolean includeUseUppercase = binding.useUppercase.isChecked();

            // Генерация пароля
            String password = generatorCode.generatePassword(length, includeUseUppercase, includeUseLowercase, includeSpecialSymbols);

            // Вывод результата
            binding.passwordOutput.setText(password);
        });

        // Слушатель кнопки "Скопировать"
        binding.copyButton.setOnClickListener(v -> {
            String password = binding.passwordOutput.getText().toString();

            // Проверяем, пустое ли поле или содержит подсказку
            if (password.isEmpty() || password.equals("Пароль появится здесь") || password.startsWith("Введите длину")) {
                // Показываем тост с сообщением об отсутствии пароля для копирования
                Toast.makeText(this, "Нет пароля для копирования", Toast.LENGTH_SHORT).show();
                return;
            }

            // Копируем пароль в буфер обмена
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Password", password);
            clipboard.setPrimaryClip(clip);

            // Показываем тост с подтверждением
            Toast.makeText(this, "Пароль скопирован в буфер обмена", Toast.LENGTH_SHORT).show();
        });
    }
}
