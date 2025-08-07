package com.pavdem.secretgenerator;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.pavdem.secretgenerator.databinding.ActivityMainBinding;
import com.pavdem.secretgenerator.generator.GeneratorCode;



public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; // переменная для View Binding
    public GeneratorCode generatorCode=new GeneratorCode();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Инициализация биндинга
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Слушатель кнопки
        binding.generateButton.setOnClickListener(v -> {
            // Генерация пароля
            String password = generatorCode.generatePassword(4, true, true, true);

            // Отображение результата в TextView
            binding.passwordOutput.setText(password);
        });
    }
}
