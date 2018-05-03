import React from 'react';
import {
    View,
    Text,
    TouchableOpacity
} from 'react-native';

const Button = (props) => {
    return (
        <View style={{ flex: 1 }}>
            <TouchableOpacity style={styles.buttonStyle} onPress={props.onPress}>
            <Text style={styles.textStyle}>{props.buttonTitle}</Text>
            </TouchableOpacity>
        </View>
    );
};

const styles = {
    buttonStyle: {
        alignSelf: 'stretch',
        backgroundColor: '#fff',
        borderRadius: 5,
        borderWidth: 1,
        borderColor: '#007aff',
        paddingLeft: 20,
        paddingRight: 20,
        alignItems: 'center',
        justifyContent: 'center'
    },
    textStyle: {
        alignSelf: 'center',
        color: '#007aff',
        fontSize: 18,
        fontWeight: '600',
        paddingTop: 10,
        paddingBottom: 10
    }
};

export default Button;
