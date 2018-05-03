/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Text,
  TouchableOpacity
} from 'react-native';

export default class App extends Component {
  render() {
    return (
      <TouchableOpacity>
        <Text>Touch me!</Text>
      </TouchableOpacity>
    );
  }
}