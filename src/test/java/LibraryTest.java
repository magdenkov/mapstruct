/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import com.google.protobuf.BoolValue;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;

import java.util.Optional;

import static org.junit.Assert.*;

public class LibraryTest {

    @Test public void testSomeLibraryMethod() {
        for (int i = 0; i < 20000; i++) {
            ModelMapper modelMapper = getModelMapper();
            BooleanDto booleanDto = new BooleanDto();
            booleanDto.setBoolValue(true);

            BoolValueDto boolValueDto = modelMapper.map(booleanDto, BoolValueDto.class);

            assertTrue(boolValueDto.getBoolValue().getValue());
        }
    }

    private ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addConverter(GRPC_BOOL_WRAPPER_TO_BOOLEAN);
        modelMapper.addConverter(BOOLEAN_TO_GRPC_WRAPPER_BOOL);
        return modelMapper;
    }

    @Before
    public void initMapper() {

    }

    private static final Converter GRPC_BOOL_WRAPPER_TO_BOOLEAN = new Converter<BoolValue, Boolean>() {
        @Override
        public Boolean convert(MappingContext<BoolValue, Boolean> context) {
            System.out.println("Converter executed --------------");
            return Optional.ofNullable(context.getSource())
                    .map(source -> new Boolean(source.getValue()))
                    .orElse(null);
        }
    };

    private static final Converter BOOLEAN_TO_GRPC_WRAPPER_BOOL = new Converter<Boolean, BoolValue>() {
        @Override
        public BoolValue convert(MappingContext<Boolean, BoolValue> context) {
            System.out.println("Converter  executed --------------");
            return Optional.ofNullable(context.getSource())
                    .map(v -> BoolValue.newBuilder().setValue(v).build())
                    .orElse(BoolValue.newBuilder().clearValue().build());
        }
    };
}
