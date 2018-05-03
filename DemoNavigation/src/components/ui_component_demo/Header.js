import React, { Component } from 'react';
import {
    View,
    Text
} from 'react-native';

export default class Header extends Component {
    render() {
        const { header, text } = styles;
        return (
            <View style={header}>
                <Text style={text}>{ this.props.headerTitle}</Text>
            </View>
        );
    }
}

const styles = {
    header: {
        backgroundColor: '#F8F8F8',
        justifyContent: 'center',
        alignItems: 'center',
        height: 60,
        paddingTop: 15,
        shadowColor: '#000',
        shadowOffset: {
            width: 0,
            height: 5
        },
        shadowOpacity: 0.2
    },
    text: {
        fontSize: 20
    }
};

