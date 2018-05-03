import React, { Component } from 'react';
import {
    View,
    Text,
    Image
} from 'react-native';

export default class BaseView extends Component {
    render() {
        return (
            <View>
                <Text style={{ fontSize: 20, fontStyle: 'italic' }}>This is a textview</Text>

            </View>
        );
    }
}
