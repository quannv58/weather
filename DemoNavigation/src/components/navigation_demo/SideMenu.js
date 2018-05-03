import React, { Component } from 'react';
import { 
    View,
    Text } from 'react-native';

export default class SideMenu extends Component {
    render() {
        return (
            <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
                <Text>SideMenu</Text>
            </View>
        );
    }
}
